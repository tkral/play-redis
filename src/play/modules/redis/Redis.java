package play.modules.redis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.BuilderFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.ZParams;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * Redis connector for Play.
 * 
 * @author Tim Kral
 */
public class Redis {

	static JedisPool connectionPool;  // Single instance pool
    static ShardedJedisPool shardedConnectionPool;  // Sharded instance pool
    
    static ThreadLocal<Jedis> redisConnection = new ThreadLocal<Jedis>();
    static ThreadLocal<ShardedJedis> shardedConnection = new ThreadLocal<ShardedJedis>();
    
    public static boolean isSharded() {
    	return shardedConnectionPool != null;
    }
    
    public static Jedis getRawConnection() {
    	if (!isSharded()) {
    		return getRawConnectionInternal();
    	} else {
    		throw new JedisConnectionException("Cannot retrieve raw connection from sharded instance. Try getRawConnectionFromShard(key).");
    	}
    }
    
    private static Jedis getRawConnectionInternal() {
		// Try to use a connection already leased in the request
		if (redisConnection.get() != null) {
			return redisConnection.get();
		}
	
		Jedis jedis = connectionPool.getResource();
		redisConnection.set(jedis);
		return jedis;
    }
    
    public static Jedis getRawConnectionFromShard(String key) {
    	if (!isSharded()) {
    		return getRawConnectionInternal();
    	} else {
    		return getRawConnectionFromShardInternal(key);
    	}
    }
    
    private static Jedis getRawConnectionFromShardInternal(String key) {
		ShardedJedis sjedis = getRawShardedConnectionInternal();
		Jedis jedis = sjedis.getShard(key);
		return jedis;
    }
    
    private static ShardedJedis getRawShardedConnectionInternal() {
		// Try to use a sharded connection already leased in the request
		if (shardedConnection.get() != null) {
			return shardedConnection.get();
		}

		ShardedJedis sjedis = shardedConnectionPool.getResource();
		shardedConnection.set(sjedis);
		return sjedis;
    }
    
    /**
     * Close the last connection that was leased.
     */
    public static void closeConnection() {
        if (redisConnection.get() != null) {
        	connectionPool.returnResource(redisConnection.get());
        	redisConnection.remove();
        } else if (shardedConnection.get() != null) {
        	shardedConnectionPool.returnResource(shardedConnection.get());
        	shardedConnection.remove();
        }
    }    
    /**
     * Destroy the connection pool.
     */
    public static void destroy() {
    	if (!isSharded()) {
    		connectionPool.destroy();
    	} else {
    		shardedConnectionPool.destroy();
    	}
    }
    
    // ==================== Redis Commands ========================
    
    public static String set(final String key, String value) {
    	Jedis jedis = getRawConnectionFromShard(key);
    	return jedis.set(key, value);
    }
    
    public static String get(final String key) {
    	Jedis jedis = getRawConnectionFromShard(key);
    	return jedis.get(key);
    }
    
    public static Boolean exists(final String key) {
    	Jedis jedis = getRawConnectionFromShard(key);
    	return jedis.exists(key);
    }
    
    public static Long del(final String... keys) {
    	if (!isSharded()) {
    		Jedis jedis = getRawConnectionInternal();
    		return jedis.del(keys);
    	} else {
    		Long deletedKeys  = 0L;
    		for (String key : keys) {
    			Jedis jedis = getRawConnectionFromShardInternal(key);
    			deletedKeys += jedis.del(key);
    		}
    		
    		return deletedKeys;
    	}
    }
    
    public static String type(final String key) {
    	Jedis jedis = getRawConnectionFromShard(key);
    	return jedis.type(key);
    }
    
    public static String flushDB() {
    	if (!isSharded()) {
    		Jedis jedis = getRawConnectionInternal();
    		return jedis.flushDB();
    	} else {
    		StringBuffer statusCodes = new StringBuffer();
    		ShardedJedis sjedis = getRawShardedConnectionInternal();
    		for (Jedis jedis : sjedis.getAllShards()) {
    			statusCodes.append(' ').append(jedis.flushDB()).append(' ');
    		}
    		
    		return statusCodes.toString();
    	}
    }
    
    public static Set<String> keys(final String pattern) {
    	if (!isSharded()) {
    		Jedis jedis = getRawConnectionInternal();
    		return jedis.keys(pattern);
    	} else {
    		throw new JedisConnectionException("Cannot match key pattern across shards.");
    	}
    }
    
    public static String rename(final String oldkey, final String newkey) {
    	if (!isSharded()) {
    		Jedis jedis = getRawConnectionInternal();
    		return jedis.rename(oldkey, newkey);
    	} else {
    		throw new JedisConnectionException("Cannot rename keys across shards.");
    	}
    }

    public static Long renameFailIfExists(final String oldkey, final String newkey) {
    	if (!isSharded()) {
    		Jedis jedis = getRawConnectionInternal();
    		return jedis.renamenx(oldkey, newkey);
    	} else {
    		throw new JedisConnectionException("Cannot rename keys across shards.");
    	}
    }

    public static Long dbSize() {
    	if (!isSharded()) {
    		Jedis jedis = getRawConnectionInternal();
    		return jedis.dbSize();
    	} else {
    		Long dbSize = 0L;
    		ShardedJedis sjedis = getRawShardedConnectionInternal();
    		for (Jedis jedis : sjedis.getAllShards()) {
    			dbSize += jedis.dbSize();
    		}
    		
    		return dbSize;
    	}
    }

    public static Long expire(final String key, final int seconds) {
    	Jedis jedis = getRawConnectionFromShard(key);
    	return jedis.expire(key, seconds);
    }
    
    public Long expireAt(final String key, final long unixTime) {
    	Jedis jedis = getRawConnectionFromShard(key);
    	return jedis.expireAt(key, unixTime);
    }

    public Long timeToLive(final String key) {
    	Jedis jedis = getRawConnectionFromShard(key);
    	return jedis.ttl(key);
    }
    
    public String select(final int index) {
        checkIsInMulti();
        client.select(index);
        return client.getStatusCodeReply();
    }

    /**
     * Move the specified key from the currently selected DB to the specified
     * destination DB. Note that this command returns 1 only if the key was
     * successfully moved, and 0 if the target key was already there or if the
     * source key was not found at all, so it is possible to use MOVE as a
     * locking primitive.
     * 
     * @param key
     * @param dbIndex
     * @return Integer reply, specifically: 1 if the key was moved 0 if the key
     *         was not moved because already present on the target DB or was not
     *         found in the current DB.
     */
    public Long move(final String key, final int dbIndex) {
        checkIsInMulti();
        client.move(key, dbIndex);
        return client.getIntegerReply();
    }

    /**
     * Delete all the keys of all the existing databases, not just the currently
     * selected one. This command never fails.
     * 
     * @return Status code reply
     */

    public String flushAll() {
        checkIsInMulti();
        client.flushAll();
        return client.getStatusCodeReply();
    }

    /**
     * GETSET is an atomic set this value and return the old value command. Set
     * key to the string value and return the old value stored at key. The
     * string can't be longer than 1073741824 bytes (1 GB).
     * <p>
     * Time complexity: O(1)
     * 
     * @param key
     * @param value
     * @return Bulk reply
     */
    public String getSet(final String key, final String value) {
        checkIsInMulti();
        client.getSet(key, value);
        return client.getBulkReply();
    }

    /**
     * Get the values of all the specified keys. If one or more keys dont exist
     * or is not of type String, a 'nil' value is returned instead of the value
     * of the specified key, but the operation never fails.
     * <p>
     * Time complexity: O(1) for every key
     * 
     * @param keys
     * @return Multi bulk reply
     */
    public List<String> mget(final String... keys) {
        checkIsInMulti();
        client.mget(keys);
        return client.getMultiBulkReply();
    }

    /**
     * SETNX works exactly like {@link #set(String, String) SET} with the only
     * difference that if the key already exists no operation is performed.
     * SETNX actually means "SET if Not eXists".
     * <p>
     * Time complexity: O(1)
     * 
     * @param key
     * @param value
     * @return Integer reply, specifically: 1 if the key was set 0 if the key
     *         was not set
     */
    public Long setnx(final String key, final String value) {
        checkIsInMulti();
        client.setnx(key, value);
        return client.getIntegerReply();
    }

    /**
     * The command is exactly equivalent to the following group of commands:
     * {@link #set(String, String) SET} + {@link #expire(String, int) EXPIRE}.
     * The operation is atomic.
     * <p>
     * Time complexity: O(1)
     * 
     * @param key
     * @param seconds
     * @param value
     * @return Status code reply
     */
    public String setex(final String key, final int seconds, final String value) {
        checkIsInMulti();
        client.setex(key, seconds, value);
        return client.getStatusCodeReply();
    }

    /**
     * Set the the respective keys to the respective values. MSET will replace
     * old values with new values, while {@link #msetnx(String...) MSETNX} will
     * not perform any operation at all even if just a single key already
     * exists.
     * <p>
     * Because of this semantic MSETNX can be used in order to set different
     * keys representing different fields of an unique logic object in a way
     * that ensures that either all the fields or none at all are set.
     * <p>
     * Both MSET and MSETNX are atomic operations. This means that for instance
     * if the keys A and B are modified, another client talking to Redis can
     * either see the changes to both A and B at once, or no modification at
     * all.
     * 
     * @see #msetnx(String...)
     * 
     * @param keysvalues
     * @return Status code reply Basically +OK as MSET can't fail
     */
    public String mset(final String... keysvalues) {
        checkIsInMulti();
        client.mset(keysvalues);
        return client.getStatusCodeReply();
    }

    /**
     * Set the the respective keys to the respective values.
     * {@link #mset(String...) MSET} will replace old values with new values,
     * while MSETNX will not perform any operation at all even if just a single
     * key already exists.
     * <p>
     * Because of this semantic MSETNX can be used in order to set different
     * keys representing different fields of an unique logic object in a way
     * that ensures that either all the fields or none at all are set.
     * <p>
     * Both MSET and MSETNX are atomic operations. This means that for instance
     * if the keys A and B are modified, another client talking to Redis can
     * either see the changes to both A and B at once, or no modification at
     * all.
     * 
     * @see #mset(String...)
     * 
     * @param keysvalues
     * @return Integer reply, specifically: 1 if the all the keys were set 0 if
     *         no key was set (at least one key already existed)
     */
    public Long msetnx(final String... keysvalues) {
        checkIsInMulti();
        client.msetnx(keysvalues);
        return client.getIntegerReply();
    }

    /**
     * IDECRBY work just like {@link #decr(String) INCR} but instead to
     * decrement by 1 the decrement is integer.
     * <p>
     * INCR commands are limited to 64 bit signed integers.
     * <p>
     * Note: this is actually a string operation, that is, in Redis there are
     * not "integer" types. Simply the string stored at the key is parsed as a
     * base 10 64 bit signed integer, incremented, and then converted back as a
     * string.
     * <p>
     * Time complexity: O(1)
     * 
     * @see #incr(String)
     * @see #decr(String)
     * @see #incrBy(String, int)
     * 
     * @param key
     * @param integer
     * @return Integer reply, this commands will reply with the new value of key
     *         after the increment.
     */
    public Long decrBy(final String key, final long integer) {
        checkIsInMulti();
        client.decrBy(key, integer);
        return client.getIntegerReply();
    }

    /**
     * Decrement the number stored at key by one. If the key does not exist or
     * contains a value of a wrong type, set the key to the value of "0" before
     * to perform the decrement operation.
     * <p>
     * INCR commands are limited to 64 bit signed integers.
     * <p>
     * Note: this is actually a string operation, that is, in Redis there are
     * not "integer" types. Simply the string stored at the key is parsed as a
     * base 10 64 bit signed integer, incremented, and then converted back as a
     * string.
     * <p>
     * Time complexity: O(1)
     * 
     * @see #incr(String)
     * @see #incrBy(String, int)
     * @see #decrBy(String, int)
     * 
     * @param key
     * @return Integer reply, this commands will reply with the new value of key
     *         after the increment.
     */
    public Long decr(final String key) {
        checkIsInMulti();
        client.decr(key);
        return client.getIntegerReply();
    }

    /**
     * INCRBY work just like {@link #incr(String) INCR} but instead to increment
     * by 1 the increment is integer.
     * <p>
     * INCR commands are limited to 64 bit signed integers.
     * <p>
     * Note: this is actually a string operation, that is, in Redis there are
     * not "integer" types. Simply the string stored at the key is parsed as a
     * base 10 64 bit signed integer, incremented, and then converted back as a
     * string.
     * <p>
     * Time complexity: O(1)
     * 
     * @see #incr(String)
     * @see #decr(String)
     * @see #decrBy(String, int)
     * 
     * @param key
     * @param integer
     * @return Integer reply, this commands will reply with the new value of key
     *         after the increment.
     */
    public Long incrBy(final String key, final long integer) {
        checkIsInMulti();
        client.incrBy(key, integer);
        return client.getIntegerReply();
    }

    /**
     * Increment the number stored at key by one. If the key does not exist or
     * contains a value of a wrong type, set the key to the value of "0" before
     * to perform the increment operation.
     * <p>
     * INCR commands are limited to 64 bit signed integers.
     * <p>
     * Note: this is actually a string operation, that is, in Redis there are
     * not "integer" types. Simply the string stored at the key is parsed as a
     * base 10 64 bit signed integer, incremented, and then converted back as a
     * string.
     * <p>
     * Time complexity: O(1)
     * 
     * @see #incrBy(String, int)
     * @see #decr(String)
     * @see #decrBy(String, int)
     * 
     * @param key
     * @return Integer reply, this commands will reply with the new value of key
     *         after the increment.
     */
    public Long incr(final String key) {
        checkIsInMulti();
        client.incr(key);
        return client.getIntegerReply();
    }

    /**
     * If the key already exists and is a string, this command appends the
     * provided value at the end of the string. If the key does not exist it is
     * created and set as an empty string, so APPEND will be very similar to SET
     * in this special case.
     * <p>
     * Time complexity: O(1). The amortized time complexity is O(1) assuming the
     * appended value is small and the already present value is of any size,
     * since the dynamic string library used by Redis will double the free space
     * available on every reallocation.
     * 
     * @param key
     * @param value
     * @return Integer reply, specifically the total length of the string after
     *         the append operation.
     */
    public Long append(final String key, final String value) {
        checkIsInMulti();
        client.append(key, value);
        return client.getIntegerReply();
    }

    /**
     * Return a subset of the string from offset start to offset end (both
     * offsets are inclusive). Negative offsets can be used in order to provide
     * an offset starting from the end of the string. So -1 means the last char,
     * -2 the penultimate and so forth.
     * <p>
     * The function handles out of range requests without raising an error, but
     * just limiting the resulting range to the actual length of the string.
     * <p>
     * Time complexity: O(start+n) (with start being the start index and n the
     * total length of the requested range). Note that the lookup part of this
     * command is O(1) so for small strings this is actually an O(1) command.
     * 
     * @param key
     * @param start
     * @param end
     * @return Bulk reply
     */
    public String substr(final String key, final int start, final int end) {
        checkIsInMulti();
        client.substr(key, start, end);
        return client.getBulkReply();
    }

    /**
     * 
     * Set the specified hash field to the specified value.
     * <p>
     * If key does not exist, a new key holding a hash is created.
     * <p>
     * <b>Time complexity:</b> O(1)
     * 
     * @param key
     * @param field
     * @param value
     * @return If the field already exists, and the HSET just produced an update
     *         of the value, 0 is returned, otherwise if a new field is created
     *         1 is returned.
     */
    public Long hset(final String key, final String field, final String value) {
        checkIsInMulti();
        client.hset(key, field, value);
        return client.getIntegerReply();
    }

    /**
     * If key holds a hash, retrieve the value associated to the specified
     * field.
     * <p>
     * If the field is not found or the key does not exist, a special 'nil'
     * value is returned.
     * <p>
     * <b>Time complexity:</b> O(1)
     * 
     * @param key
     * @param field
     * @return Bulk reply
     */
    public String hget(final String key, final String field) {
        checkIsInMulti();
        client.hget(key, field);
        return client.getBulkReply();
    }

    /**
     * 
     * Set the specified hash field to the specified value if the field not
     * exists. <b>Time complexity:</b> O(1)
     * 
     * @param key
     * @param field
     * @param value
     * @return If the field already exists, 0 is returned, otherwise if a new
     *         field is created 1 is returned.
     */
    public Long hsetnx(final String key, final String field, final String value) {
        checkIsInMulti();
        client.hsetnx(key, field, value);
        return client.getIntegerReply();
    }

    /**
     * Set the respective fields to the respective values. HMSET replaces old
     * values with new values.
     * <p>
     * If key does not exist, a new key holding a hash is created.
     * <p>
     * <b>Time complexity:</b> O(N) (with N being the number of fields)
     * 
     * @param key
     * @param hash
     * @return Return OK or Exception if hash is empty
     */
    public String hmset(final String key, final Map<String, String> hash) {
        checkIsInMulti();
        client.hmset(key, hash);
        return client.getStatusCodeReply();
    }

    /**
     * Retrieve the values associated to the specified fields.
     * <p>
     * If some of the specified fields do not exist, nil values are returned.
     * Non existing keys are considered like empty hashes.
     * <p>
     * <b>Time complexity:</b> O(N) (with N being the number of fields)
     * 
     * @param key
     * @param fields
     * @return Multi Bulk Reply specifically a list of all the values associated
     *         with the specified fields, in the same order of the request.
     */
    public List<String> hmget(final String key, final String... fields) {
        checkIsInMulti();
        client.hmget(key, fields);
        return client.getMultiBulkReply();
    }

    /**
     * Increment the number stored at field in the hash at key by value. If key
     * does not exist, a new key holding a hash is created. If field does not
     * exist or holds a string, the value is set to 0 before applying the
     * operation. Since the value argument is signed you can use this command to
     * perform both increments and decrements.
     * <p>
     * The range of values supported by HINCRBY is limited to 64 bit signed
     * integers.
     * <p>
     * <b>Time complexity:</b> O(1)
     * 
     * @param key
     * @param field
     * @param value
     * @return Integer reply The new value at field after the increment
     *         operation.
     */
    public Long hincrBy(final String key, final String field, final long value) {
        checkIsInMulti();
        client.hincrBy(key, field, value);
        return client.getIntegerReply();
    }

    /**
     * Test for existence of a specified field in a hash.
     * 
     * <b>Time complexity:</b> O(1)
     * 
     * @param key
     * @param field
     * @return Return 1 if the hash stored at key contains the specified field.
     *         Return 0 if the key is not found or the field is not present.
     */
    public Boolean hexists(final String key, final String field) {
        checkIsInMulti();
        client.hexists(key, field);
        return client.getIntegerReply() == 1;
    }

    /**
     * Remove the specified field from an hash stored at key.
     * <p>
     * <b>Time complexity:</b> O(1)
     * 
     * @param key
     * @param field
     * @return If the field was present in the hash it is deleted and 1 is
     *         returned, otherwise 0 is returned and no operation is performed.
     */
    public Long hdel(final String key, final String field) {
        checkIsInMulti();
        client.hdel(key, field);
        return client.getIntegerReply();
    }

    /**
     * Return the number of items in a hash.
     * <p>
     * <b>Time complexity:</b> O(1)
     * 
     * @param key
     * @return The number of entries (fields) contained in the hash stored at
     *         key. If the specified key does not exist, 0 is returned assuming
     *         an empty hash.
     */
    public Long hlen(final String key) {
        checkIsInMulti();
        client.hlen(key);
        return client.getIntegerReply();
    }

    /**
     * Return all the fields in a hash.
     * <p>
     * <b>Time complexity:</b> O(N), where N is the total number of entries
     * 
     * @param key
     * @return All the fields names contained into a hash.
     */
    public Set<String> hkeys(final String key) {
        checkIsInMulti();
        client.hkeys(key);
        return BuilderFactory.STRING_SET
                .build(client.getBinaryMultiBulkReply());
    }

    /**
     * Return all the values in a hash.
     * <p>
     * <b>Time complexity:</b> O(N), where N is the total number of entries
     * 
     * @param key
     * @return All the fields values contained into a hash.
     */
    public List<String> hvals(final String key) {
        checkIsInMulti();
        client.hvals(key);
        final List<String> lresult = client.getMultiBulkReply();
        return lresult;
    }

    /**
     * Return all the fields and associated values in a hash.
     * <p>
     * <b>Time complexity:</b> O(N), where N is the total number of entries
     * 
     * @param key
     * @return All the fields and values contained into a hash.
     */
    public Map<String, String> hgetAll(final String key) {
        checkIsInMulti();
        client.hgetAll(key);
        return BuilderFactory.STRING_MAP
                .build(client.getBinaryMultiBulkReply());
    }

    /**
     * Add the string value to the head (LPUSH) or tail (RPUSH) of the list
     * stored at key. If the key does not exist an empty list is created just
     * before the append operation. If the key exists but is not a List an error
     * is returned.
     * <p>
     * Time complexity: O(1)
     * 
     * @see Jedis#lpush(String, String)
     * 
     * @param key
     * @param string
     * @return Integer reply, specifically, the number of elements inside the
     *         list after the push operation.
     */
    public Long rpush(final String key, final String string) {
        checkIsInMulti();
        client.rpush(key, string);
        return client.getIntegerReply();
    }

    /**
     * Add the string value to the head (LPUSH) or tail (RPUSH) of the list
     * stored at key. If the key does not exist an empty list is created just
     * before the append operation. If the key exists but is not a List an error
     * is returned.
     * <p>
     * Time complexity: O(1)
     * 
     * @see Jedis#rpush(String, String)
     * 
     * @param key
     * @param string
     * @return Integer reply, specifically, the number of elements inside the
     *         list after the push operation.
     */
    public Long lpush(final String key, final String string) {
        checkIsInMulti();
        client.lpush(key, string);
        return client.getIntegerReply();
    }

    /**
     * Return the length of the list stored at the specified key. If the key
     * does not exist zero is returned (the same behaviour as for empty lists).
     * If the value stored at key is not a list an error is returned.
     * <p>
     * Time complexity: O(1)
     * 
     * @param key
     * @return The length of the list.
     */
    public Long llen(final String key) {
        checkIsInMulti();
        client.llen(key);
        return client.getIntegerReply();
    }

    /**
     * Return the specified elements of the list stored at the specified key.
     * Start and end are zero-based indexes. 0 is the first element of the list
     * (the list head), 1 the next element and so on.
     * <p>
     * For example LRANGE foobar 0 2 will return the first three elements of the
     * list.
     * <p>
     * start and end can also be negative numbers indicating offsets from the
     * end of the list. For example -1 is the last element of the list, -2 the
     * penultimate element and so on.
     * <p>
     * <b>Consistency with range functions in various programming languages</b>
     * <p>
     * Note that if you have a list of numbers from 0 to 100, LRANGE 0 10 will
     * return 11 elements, that is, rightmost item is included. This may or may
     * not be consistent with behavior of range-related functions in your
     * programming language of choice (think Ruby's Range.new, Array#slice or
     * Python's range() function).
     * <p>
     * LRANGE behavior is consistent with one of Tcl.
     * <p>
     * <b>Out-of-range indexes</b>
     * <p>
     * Indexes out of range will not produce an error: if start is over the end
     * of the list, or start > end, an empty list is returned. If end is over
     * the end of the list Redis will threat it just like the last element of
     * the list.
     * <p>
     * Time complexity: O(start+n) (with n being the length of the range and
     * start being the start offset)
     * 
     * @param key
     * @param start
     * @param end
     * @return Multi bulk reply, specifically a list of elements in the
     *         specified range.
     */
    public List<String> lrange(final String key, final long start, final long end) {
        checkIsInMulti();
        client.lrange(key, start, end);
        return client.getMultiBulkReply();
    }

    /**
     * Trim an existing list so that it will contain only the specified range of
     * elements specified. Start and end are zero-based indexes. 0 is the first
     * element of the list (the list head), 1 the next element and so on.
     * <p>
     * For example LTRIM foobar 0 2 will modify the list stored at foobar key so
     * that only the first three elements of the list will remain.
     * <p>
     * start and end can also be negative numbers indicating offsets from the
     * end of the list. For example -1 is the last element of the list, -2 the
     * penultimate element and so on.
     * <p>
     * Indexes out of range will not produce an error: if start is over the end
     * of the list, or start > end, an empty list is left as value. If end over
     * the end of the list Redis will threat it just like the last element of
     * the list.
     * <p>
     * Hint: the obvious use of LTRIM is together with LPUSH/RPUSH. For example:
     * <p>
     * {@code lpush("mylist", "someelement"); ltrim("mylist", 0, 99); * }
     * <p>
     * The above two commands will push elements in the list taking care that
     * the list will not grow without limits. This is very useful when using
     * Redis to store logs for example. It is important to note that when used
     * in this way LTRIM is an O(1) operation because in the average case just
     * one element is removed from the tail of the list.
     * <p>
     * Time complexity: O(n) (with n being len of list - len of range)
     * 
     * @param key
     * @param start
     * @param end
     * @return Status code reply
     */
    public String ltrim(final String key, final long start, final long end) {
        checkIsInMulti();
        client.ltrim(key, start, end);
        return client.getStatusCodeReply();
    }

    /**
     * Return the specified element of the list stored at the specified key. 0
     * is the first element, 1 the second and so on. Negative indexes are
     * supported, for example -1 is the last element, -2 the penultimate and so
     * on.
     * <p>
     * If the value stored at key is not of list type an error is returned. If
     * the index is out of range a 'nil' reply is returned.
     * <p>
     * Note that even if the average time complexity is O(n) asking for the
     * first or the last element of the list is O(1).
     * <p>
     * Time complexity: O(n) (with n being the length of the list)
     * 
     * @param key
     * @param index
     * @return Bulk reply, specifically the requested element
     */
    public String lindex(final String key, final long index) {
        checkIsInMulti();
        client.lindex(key, index);
        return client.getBulkReply();
    }

    /**
     * Set a new value as the element at index position of the List at key.
     * <p>
     * Out of range indexes will generate an error.
     * <p>
     * Similarly to other list commands accepting indexes, the index can be
     * negative to access elements starting from the end of the list. So -1 is
     * the last element, -2 is the penultimate, and so forth.
     * <p>
     * <b>Time complexity:</b>
     * <p>
     * O(N) (with N being the length of the list), setting the first or last
     * elements of the list is O(1).
     * 
     * @see #lindex(String, int)
     * 
     * @param key
     * @param index
     * @param value
     * @return Status code reply
     */
    public String lset(final String key, final long index, final String value) {
        checkIsInMulti();
        client.lset(key, index, value);
        return client.getStatusCodeReply();
    }

    /**
     * Remove the first count occurrences of the value element from the list. If
     * count is zero all the elements are removed. If count is negative elements
     * are removed from tail to head, instead to go from head to tail that is
     * the normal behaviour. So for example LREM with count -2 and hello as
     * value to remove against the list (a,b,c,hello,x,hello,hello) will lave
     * the list (a,b,c,hello,x). The number of removed elements is returned as
     * an integer, see below for more information about the returned value. Note
     * that non existing keys are considered like empty lists by LREM, so LREM
     * against non existing keys will always return 0.
     * <p>
     * Time complexity: O(N) (with N being the length of the list)
     * 
     * @param key
     * @param count
     * @param value
     * @return Integer Reply, specifically: The number of removed elements if
     *         the operation succeeded
     */
    public Long lrem(final String key, final long count, final String value) {
        checkIsInMulti();
        client.lrem(key, count, value);
        return client.getIntegerReply();
    }

    /**
     * Atomically return and remove the first (LPOP) or last (RPOP) element of
     * the list. For example if the list contains the elements "a","b","c" LPOP
     * will return "a" and the list will become "b","c".
     * <p>
     * If the key does not exist or the list is already empty the special value
     * 'nil' is returned.
     * 
     * @see #rpop(String)
     * 
     * @param key
     * @return Bulk reply
     */
    public String lpop(final String key) {
        checkIsInMulti();
        client.lpop(key);
        return client.getBulkReply();
    }

    /**
     * Atomically return and remove the first (LPOP) or last (RPOP) element of
     * the list. For example if the list contains the elements "a","b","c" LPOP
     * will return "a" and the list will become "b","c".
     * <p>
     * If the key does not exist or the list is already empty the special value
     * 'nil' is returned.
     * 
     * @see #lpop(String)
     * 
     * @param key
     * @return Bulk reply
     */
    public String rpop(final String key) {
        checkIsInMulti();
        client.rpop(key);
        return client.getBulkReply();
    }

    /**
     * Atomically return and remove the last (tail) element of the srckey list,
     * and push the element as the first (head) element of the dstkey list. For
     * example if the source list contains the elements "a","b","c" and the
     * destination list contains the elements "foo","bar" after an RPOPLPUSH
     * command the content of the two lists will be "a","b" and "c","foo","bar".
     * <p>
     * If the key does not exist or the list is already empty the special value
     * 'nil' is returned. If the srckey and dstkey are the same the operation is
     * equivalent to removing the last element from the list and pusing it as
     * first element of the list, so it's a "list rotation" command.
     * <p>
     * Time complexity: O(1)
     * 
     * @param srckey
     * @param dstkey
     * @return Bulk reply
     */
    public String rpoplpush(final String srckey, final String dstkey) {
        checkIsInMulti();
        client.rpoplpush(srckey, dstkey);
        return client.getBulkReply();
    }

    /**
     * Add the specified member to the set value stored at key. If member is
     * already a member of the set no operation is performed. If key does not
     * exist a new set with the specified member as sole member is created. If
     * the key exists but does not hold a set value an error is returned.
     * <p>
     * Time complexity O(1)
     * 
     * @param key
     * @param member
     * @return Integer reply, specifically: 1 if the new element was added 0 if
     *         the element was already a member of the set
     */
    public Long sadd(final String key, final String member) {
        checkIsInMulti();
        client.sadd(key, member);
        return client.getIntegerReply();
    }

    /**
     * Return all the members (elements) of the set value stored at key. This is
     * just syntax glue for {@link #sinter(String...) SINTER}.
     * <p>
     * Time complexity O(N)
     * 
     * @param key
     * @return Multi bulk reply
     */
    public Set<String> smembers(final String key) {
        checkIsInMulti();
        client.smembers(key);
        final List<String> members = client.getMultiBulkReply();
        return new HashSet<String>(members);
    }

    /**
     * Remove the specified member from the set value stored at key. If member
     * was not a member of the set no operation is performed. If key does not
     * hold a set value an error is returned.
     * <p>
     * Time complexity O(1)
     * 
     * @param key
     * @param member
     * @return Integer reply, specifically: 1 if the new element was removed 0
     *         if the new element was not a member of the set
     */
    public Long srem(final String key, final String member) {
        checkIsInMulti();
        client.srem(key, member);
        return client.getIntegerReply();
    }

    /**
     * Remove a random element from a Set returning it as return value. If the
     * Set is empty or the key does not exist, a nil object is returned.
     * <p>
     * The {@link #srandmember(String)} command does a similar work but the
     * returned element is not removed from the Set.
     * <p>
     * Time complexity O(1)
     * 
     * @param key
     * @return Bulk reply
     */
    public String spop(final String key) {
        checkIsInMulti();
        client.spop(key);
        return client.getBulkReply();
    }

    public Long setMove(final String srckey, final String dstkey, final String member) {
    	if (!isSharded()) {
    		Jedis jedis = getRawConnectionInternal();
    		return jedis.smove(srckey, dstkey, member);
    	} else {
    		throw new JedisConnectionException("Cannot move sets with multiple shards.");
    	}
    }

    public Long setCardinality(final String key) {
    	Jedis jedis = getRawConnectionFromShard(key);
    	return jedis.scard(key);
    }

    public Boolean setIsMember(final String key, final String member) {
    	Jedis jedis = getRawConnectionFromShard(key);
    	return jedis.sismember(key, member);
    }

    public Set<String> setIntersection(final String... keys) {
    	if (!isSharded()) {
    		Jedis jedis = getRawConnectionInternal();
    		return jedis.sinter(keys);
    	} else {
    		throw new JedisConnectionException("Cannot perform set union with multiple shards.");
    	}
    }

    public Long setIntersectionStore(final String dstkey, final String... keys) {
    	if (!isSharded()) {
    		Jedis jedis = getRawConnectionInternal();
    		return jedis.sinterstore(dstkey, keys);
    	} else {
    		throw new JedisConnectionException("Cannot perform set union with multiple shards.");
    	}
    }

    public Set<String> setUnion(final String... keys) {
    	if (!isSharded()) {
    		Jedis jedis = getRawConnectionInternal();
    		return jedis.sunion(keys);
    	} else {
    		throw new JedisConnectionException("Cannot perform set union with multiple shards.");
    	}
    }

    public Long setUnionStore(final String dstkey, final String... keys) {
    	if (!isSharded()) {
    		Jedis jedis = getRawConnectionInternal();
    		return jedis.sunionstore(dstkey, keys);
    	} else {
    		throw new JedisConnectionException("Cannot perform set union with multiple shards.");
    	}
    }

    public Set<String> setDifference(final String... keys) {
    	if (!isSharded()) {
    		Jedis jedis = getRawConnectionInternal();
    		return jedis.sdiff(keys);
    	} else {
    		throw new JedisConnectionException("Cannot perform set difference with multiple shards.");
    	}
    }

    public Long setDifferenceStore(final String dstkey, final String... keys) {
    	if (!isSharded()) {
    		Jedis jedis = getRawConnectionInternal();
    		return jedis.sdiffstore(dstkey, keys);
    	} else {
    		throw new JedisConnectionException("Cannot perform set difference with multiple shards.");
    	}
    }

    public String setRandomMember(final String key) {
    	Jedis jedis = getRawConnectionFromShard(key);
    	return jedis.srandmember(key);
    }

    public Long sortedSetAdd(final String key, final double score, final String member) {
    	Jedis jedis = getRawConnectionFromShard(key);
    	return jedis.zadd(key, score, member);
    }

    public Set<String> sortedSetRange(final String key, final int start, final int end) {
    	Jedis jedis = getRawConnectionFromShard(key);
    	return jedis.zrange(key, start, end);
    }

    public Long sortedSetRemove(final String key, final String member) {
    	Jedis jedis = getRawConnectionFromShard(key);
    	return jedis.zrem(key, member);
    }

    public Double sortedSetIncrBy(final String key, final double score, final String member) {
    	Jedis jedis = getRawConnectionFromShard(key);
    	return jedis.zincrby(key, score, member);
    }

    public Long sortedSetAscRank(final String key, final String member) {
    	Jedis jedis = getRawConnectionFromShard(key);
    	return jedis.zrank(key, member);
    }

    public Long sortedSetDescRank(final String key, final String member) {
    	Jedis jedis = getRawConnectionFromShard(key);
    	return jedis.zrevrank(key, member);
    }

    public Set<String> sortedSetDescRange(final String key, final int start, final int end) {
    	Jedis jedis = getRawConnectionFromShard(key);
    	return jedis.zrevrange(key, start, end);
    }

    public Set<Tuple> sortedSetAscRangeWithScores(final String key, final int start, final int end) {
    	Jedis jedis = getRawConnectionFromShard(key);
    	return jedis.zrangeWithScores(key, start, end);
    }

    public Set<Tuple> sortedSetDescRangeWithScores(final String key, final int start, final int end) {
    	Jedis jedis = getRawConnectionFromShard(key);
    	return jedis.zrevrangeWithScores(key, start, end);
    }

    public Long sortedSetCardinality(final String key) {
    	Jedis jedis = getRawConnectionFromShard(key);
    	return jedis.zcard(key);
    }

    public Double sortedSetScore(final String key, final String member) {
    	Jedis jedis = getRawConnectionFromShard(key);
    	return jedis.zscore(key, member);
    }

    // == TODO
    /*
    public String watch(final String... keys) {
        client.watch(keys);
        return client.getStatusCodeReply();
    }
	*/

    public List<String> sort(final String key) {
    	Jedis jedis = getRawConnectionFromShard(key);
    	return jedis.sort(key);
    }

    public List<String> sort(final String key, final SortingParams sortingParameters) {
    	Jedis jedis = getRawConnectionFromShard(key);
    	return jedis.sort(key, sortingParameters);
    }

    public List<String> listHeadPopBlocking(final int timeout, final String... keys) {
    	if (!isSharded()) {
    		Jedis jedis = getRawConnectionInternal();
    		return jedis.blpop(timeout, keys);
    	} else {
    		throw new JedisConnectionException("Cannot perform blocking operations across shards.");
    	}
    }

    public Long sort(final String key, final SortingParams sortingParameters,
            final String dstkey) {
    	if (!isSharded()) {
    		Jedis jedis = getRawConnectionInternal();
    		return jedis.sort(key, sortingParameters, dstkey);
    	} else {
    		throw new JedisConnectionException("Cannot store sorted collection with multiple shards.");
    	}
    }

    public Long sort(final String key, final String dstkey) {
    	if (!isSharded()) {
    		Jedis jedis = getRawConnectionInternal();
    		return jedis.sort(key, dstkey);
    	} else {
    		throw new JedisConnectionException("Cannot store sorted collection with multiple shards.");
    	}
    }

    public List<String> listTailPopBlocking(final int timeout, final String... keys) {
    	if (!isSharded()) {
    		Jedis jedis = getRawConnectionInternal();
    		return jedis.brpop(timeout, keys);
    	} else {
    		throw new JedisConnectionException("Cannot perform blocking operations across shards.");
    	}
    }

    // == TODO
    /*
    public void subscribe(JedisPubSub jedisPubSub, String... channels) {
        checkIsInMulti();
        connect();
        client.setTimeoutInfinite();
        jedisPubSub.proceed(client, channels);
        client.rollbackTimeout();
    }

    public Long publish(String channel, String message) {
        checkIsInMulti();
        client.publish(channel, message);
        return client.getIntegerReply();
    }

    public void psubscribe(JedisPubSub jedisPubSub, String... patterns) {
        checkIsInMulti();
        connect();
        client.setTimeoutInfinite();
        jedisPubSub.proceedWithPatterns(client, patterns);
        client.rollbackTimeout();
    }
    */

    public Long sortedSetCount(final String key, final double min, final double max) {
    	Jedis jedis = getRawConnectionFromShard(key);
        return jedis.zcount(key, min, max);
    }

    public Set<String> sortedSetAscRangeByScore(final String key, final double min,
            final double max) {
    	Jedis jedis = getRawConnectionFromShard(key);
        return jedis.zrangeByScore(key, min, max);
    }

    public Set<String> sortedSetAscRangeByScore(final String key, final String min,
            final String max) {
    	Jedis jedis = getRawConnectionFromShard(key);
        return jedis.zrangeByScore(key, min, max);
    }

    public Set<String> sortedSetAscRangeByScore(final String key, final double min,
            final double max, final int offset, final int count) {
    	Jedis jedis = getRawConnectionFromShard(key);
        return jedis.zrangeByScore(key, min, max, offset, count);
    }

    public Set<Tuple> sortedSetAscRangeByScoreWithScores(final String key,
            final double min, final double max) {
    	Jedis jedis = getRawConnectionFromShard(key);
        return jedis.zrangeByScoreWithScores(key, min, max);
    }

    public Set<Tuple> sortedSetAscRangeByScoreWithScores(final String key,
            final double min, final double max, final int offset,
            final int count) {
    	Jedis jedis = getRawConnectionFromShard(key);
        return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
    }

    public Set<String> sortedSetDescRangeByScore(final String key, final double max,
            final double min) {
    	Jedis jedis = getRawConnectionFromShard(key);
    	return jedis.zrevrangeByScore(key, max, min);
    }

    public Set<String> sortedSetDescRangeByScore(final String key, final String max,
            final String min) {
    	Jedis jedis = getRawConnectionFromShard(key);
    	return jedis.zrevrangeByScore(key, max, min);
    }

    public static Set<String> sortedSetDescRangeByScore(final String key, final double max,
            final double min, final int offset, final int count) {
    	Jedis jedis = getRawConnectionFromShard(key);
    	return jedis.zrevrangeByScore(key, max, min, offset, count);
    }

    public Set<Tuple> sortedSetDescRangeByScoreWithScores(final String key,
            final double max, final double min) {
    	Jedis jedis = getRawConnectionFromShard(key);
    	return jedis.zrevrangeByScoreWithScores(key, max, min);
    }

    public Set<Tuple> sortedSetDescRangeByScoreWithScores(final String key,
            final double max, final double min, final int offset,
            final int count) {
    	Jedis jedis = getRawConnectionFromShard(key);
    	return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
    }

    public static Long sortedSetRemoveRangeByRank(final String key, final int start, final int end) {
    	Jedis jedis = getRawConnectionFromShard(key);
        return jedis.zremrangeByRank(key, start, end);
    }

    public static Long sortedSetRemoveRangeByScore(final String key, final double start, final double end) {
    	Jedis jedis = getRawConnectionFromShard(key);
    	return jedis.zremrangeByScore(key, start, end);
    }

    public static Long sortedSetUnionStore(final String dstkey, final String... sets) {
    	if (!isSharded()) {
    		Jedis jedis = getRawConnectionInternal();
    		return jedis.zunionstore(dstkey, sets);
    	} else {
    		throw new JedisConnectionException("Cannot union sorted sets across shards.");
    	}
    }

    public static Long sortedSetUnionStore(final String dstkey, final ZParams params, final String... sets) {
    	if (!isSharded()) {
    		Jedis jedis = getRawConnectionInternal();
    		return jedis.zunionstore(dstkey, params, sets);
    	} else {
    		throw new JedisConnectionException("Cannot union sorted sets across shards.");
    	}
    }

    public static Long sortedSetIntersectionStore(final String dstkey, final String... sets) {
    	if (!isSharded()) {
    		Jedis jedis = getRawConnectionInternal();
    		return jedis.zinterstore(dstkey, sets);
    	} else {
    		throw new JedisConnectionException("Cannot interect sorted sets across shards.");
    	}
    }

    public static Long sortedSetIntersectionStore(final String dstkey, final ZParams params, final String... sets) {
    	if (!isSharded()) {
    		Jedis jedis = getRawConnectionInternal();
    		return jedis.zinterstore(dstkey, params, sets);
    	} else {
    		throw new JedisConnectionException("Cannot interect sorted sets across shards.");
    	}
    }

    public static Long strLen(final String key) {
    	Jedis jedis = getRawConnectionFromShard(key);
    	return jedis.strlen(key);
    }

    public static Long listHeadPushIfExists(final String key, final String string) {
    	Jedis jedis = getRawConnectionFromShard(key);
    	return jedis.lpushx(key, string);
    }

    public static Long persist(final String key) {
    	Jedis jedis = getRawConnectionFromShard(key);
    	return jedis.persist(key);
    }

    public static Long listTailPushIfExists(final String key, final String string) {
    	Jedis jedis = getRawConnectionFromShard(key);
    	return jedis.rpushx(key, string);
    }

    public static String echo(final String string) {
    	if (!isSharded()) {
    		Jedis jedis = getRawConnectionInternal();
    		return jedis.echo(string);
    	} else {
    		Jedis jedis = getRawShardedConnectionInternal().getShard("random");
    		return jedis.echo(string);
    	}
    }

    public static Long listInsert(final String key, final LIST_POSITION where,
            final String pivot, final String value) {
    	Jedis jedis = getRawConnectionFromShard(key);
    	return jedis.linsert(key, where, pivot, value);
    }

    public static String listMoveTailToHeadBlocking(String source, String destination, int timeout) {
    	if (!isSharded()) {
    		Jedis jedis = getRawConnectionInternal();
    		return jedis.brpoplpush(source, destination, timeout);
    	} else {
    		throw new JedisConnectionException("Cannot perform blocking operations across shards.");
    	}
    }

    public static boolean setBit(String key, long offset, boolean value) {
    	Jedis jedis = getRawConnectionFromShard(key);
    	return jedis.setbit(key, offset, value);
    }

    public static boolean getBit(String key, long offset) {
    	Jedis jedis = getRawConnectionFromShard(key);
    	return jedis.getbit(key, offset);
    }

    public static long setRange(String key, long offset, String value) {
    	Jedis jedis = getRawConnectionFromShard(key);
    	return jedis.setrange(key, offset, value);
    }

    public static String getRange(String key, long startOffset, long endOffset) {
    	Jedis jedis = getRawConnectionFromShard(key);
    	return jedis.getrange(key, startOffset, endOffset);
    }
}
