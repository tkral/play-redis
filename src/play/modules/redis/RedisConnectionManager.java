package play.modules.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * Redis connection manager for Play.
 * 
 * @author Tim Kral
 */
public class RedisConnectionManager {

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
    
    static Jedis getRawConnectionInternal() {
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
    		ShardedJedis sjedis = getRawShardedConnectionInternal();
    		Jedis jedis = sjedis.getShard(key);
    		return jedis;
    	}
    }
    
    public static Jedis getRawConnectionFromShard(byte[] key) {
    	if (!isSharded()) {
    		return getRawConnectionInternal();
    	} else {
    		ShardedJedis sjedis = getRawShardedConnectionInternal();
    		Jedis jedis = sjedis.getShard(key);
    		return jedis;
    	}
    }
    
    static ShardedJedis getRawShardedConnectionInternal() {
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
}
