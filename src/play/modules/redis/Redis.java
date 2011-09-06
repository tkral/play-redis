package play.modules.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * Redis connector for the Play framework.
 *
 * **DO NOT EDIT**.  This class was auto-generated.
 * See play.modules.redis.generator.RedisGenerator.
 *
 * @author Tim Kral
 **/
public class Redis {


    public static java.lang.Long append(java.lang.String key,java.lang.String value) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.append(key,value);
    }
    
    public static java.lang.Long append(byte[] key,byte[] value) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.append(key,value);
    }
    
    public static java.lang.String auth(java.lang.String password) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.auth(password);
        } else {
            throw new JedisConnectionException("Cannot execute auth with sharded instance.");
        }
    }
    
    public static java.lang.String bgrewriteaof() {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.bgrewriteaof();
        } else {
            throw new JedisConnectionException("Cannot execute bgrewriteaof with sharded instance.");
        }
    }
    
    public static java.lang.String bgsave() {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.bgsave();
        } else {
            throw new JedisConnectionException("Cannot execute bgsave with sharded instance.");
        }
    }
    
    public static java.util.List blpop(int timeout,java.lang.String[] keys) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.blpop(timeout,keys);
        } else {
            throw new JedisConnectionException("Cannot execute blpop with sharded instance.");
        }
    }
    
    public static java.util.List blpop(int timeout,byte[][] keys) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.blpop(timeout,keys);
        } else {
            throw new JedisConnectionException("Cannot execute blpop with sharded instance.");
        }
    }
    
    public static java.util.List brpop(int timeout,java.lang.String[] keys) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.brpop(timeout,keys);
        } else {
            throw new JedisConnectionException("Cannot execute brpop with sharded instance.");
        }
    }
    
    public static java.util.List brpop(int timeout,byte[][] keys) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.brpop(timeout,keys);
        } else {
            throw new JedisConnectionException("Cannot execute brpop with sharded instance.");
        }
    }
    
    public static byte[] brpoplpush(byte[] source,byte[] destination,int timeout) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.brpoplpush(source,destination,timeout);
        } else {
            throw new JedisConnectionException("Cannot execute brpoplpush with sharded instance.");
        }
    }
    
    public static java.lang.String brpoplpush(java.lang.String source,java.lang.String destination,int timeout) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.brpoplpush(source,destination,timeout);
        } else {
            throw new JedisConnectionException("Cannot execute brpoplpush with sharded instance.");
        }
    }
    
    public static java.util.List configGet(java.lang.String pattern) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.configGet(pattern);
        } else {
            throw new JedisConnectionException("Cannot execute configGet with sharded instance.");
        }
    }
    
    public static java.lang.String configResetStat() {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.configResetStat();
        } else {
            throw new JedisConnectionException("Cannot execute configResetStat with sharded instance.");
        }
    }
    
    public static java.lang.String configSet(java.lang.String parameter,java.lang.String value) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.configSet(parameter,value);
        } else {
            throw new JedisConnectionException("Cannot execute configSet with sharded instance.");
        }
    }
    
    public static void connect() {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            jedis.connect();
        } else {
            throw new JedisConnectionException("Cannot execute connect with sharded instance.");
        }
    }
    
    public static java.lang.Long dbSize() {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.dbSize();
        } else {
            throw new JedisConnectionException("Cannot execute dbSize with sharded instance.");
        }
    }
    
    public static java.lang.String debug(redis.clients.jedis.DebugParams params) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.debug(params);
        } else {
            throw new JedisConnectionException("Cannot execute debug with sharded instance.");
        }
    }
    
    public static java.lang.Long decr(byte[] key) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.decr(key);
    }
    
    public static java.lang.Long decr(java.lang.String key) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.decr(key);
    }
    
    public static java.lang.Long decrBy(byte[] key,long integer) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.decrBy(key,integer);
    }
    
    public static java.lang.Long decrBy(java.lang.String key,long integer) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.decrBy(key,integer);
    }
    
    public static java.lang.Long del(java.lang.String[] keys) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.del(keys);
        } else {
            throw new JedisConnectionException("Cannot execute del with sharded instance.");
        }
    }
    
    public static java.lang.Long del(byte[][] keys) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.del(keys);
        } else {
            throw new JedisConnectionException("Cannot execute del with sharded instance.");
        }
    }
    
    public static byte[] echo(byte[] string) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.echo(string);
        } else {
            throw new JedisConnectionException("Cannot execute echo with sharded instance.");
        }
    }
    
    public static java.lang.String echo(java.lang.String string) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.echo(string);
        } else {
            throw new JedisConnectionException("Cannot execute echo with sharded instance.");
        }
    }
    
    public static java.lang.Boolean exists(java.lang.String key) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.exists(key);
    }
    
    public static java.lang.Boolean exists(byte[] key) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.exists(key);
    }
    
    public static java.lang.Long expire(java.lang.String key,int seconds) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.expire(key,seconds);
    }
    
    public static java.lang.Long expire(byte[] key,int seconds) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.expire(key,seconds);
    }
    
    public static java.lang.Long expireAt(byte[] key,long unixTime) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.expireAt(key,unixTime);
    }
    
    public static java.lang.Long expireAt(java.lang.String key,long unixTime) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.expireAt(key,unixTime);
    }
    
    public static java.lang.String flushAll() {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.flushAll();
        } else {
            throw new JedisConnectionException("Cannot execute flushAll with sharded instance.");
        }
    }
    
    public static java.lang.String flushDB() {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.flushDB();
        } else {
            throw new JedisConnectionException("Cannot execute flushDB with sharded instance.");
        }
    }
    
    public static java.lang.String get(java.lang.String key) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.get(key);
    }
    
    public static byte[] get(byte[] key) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.get(key);
    }
    
    public static redis.clients.jedis.Client getClient() {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.getClient();
        } else {
            throw new JedisConnectionException("Cannot execute getClient with sharded instance.");
        }
    }
    
    public static java.lang.Long getDB() {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.getDB();
        } else {
            throw new JedisConnectionException("Cannot execute getDB with sharded instance.");
        }
    }
    
    public static byte[] getSet(byte[] key,byte[] value) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.getSet(key,value);
    }
    
    public static java.lang.String getSet(java.lang.String key,java.lang.String value) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.getSet(key,value);
    }
    
    public static boolean getbit(java.lang.String key,long offset) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.getbit(key,offset);
    }
    
    public static java.lang.Long getbit(byte[] key,long offset) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.getbit(key,offset);
        } else {
            throw new JedisConnectionException("Cannot execute getbit with sharded instance.");
        }
    }
    
    public static java.lang.String getrange(java.lang.String key,long startOffset,long endOffset) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.getrange(key,startOffset,endOffset);
    }
    
    public static java.lang.String getrange(byte[] key,long startOffset,long endOffset) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.getrange(key,startOffset,endOffset);
        } else {
            throw new JedisConnectionException("Cannot execute getrange with sharded instance.");
        }
    }
    
    public static java.lang.Long hdel(byte[] key,byte[] field) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.hdel(key,field);
    }
    
    public static java.lang.Long hdel(java.lang.String key,java.lang.String field) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.hdel(key,field);
    }
    
    public static java.lang.Boolean hexists(java.lang.String key,java.lang.String field) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.hexists(key,field);
    }
    
    public static java.lang.Boolean hexists(byte[] key,byte[] field) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.hexists(key,field);
    }
    
    public static byte[] hget(byte[] key,byte[] field) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.hget(key,field);
    }
    
    public static java.lang.String hget(java.lang.String key,java.lang.String field) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.hget(key,field);
    }
    
    public static java.util.Map hgetAll(java.lang.String key) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.hgetAll(key);
    }
    
    public static java.util.Map hgetAll(byte[] key) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.hgetAll(key);
    }
    
    public static java.lang.Long hincrBy(java.lang.String key,java.lang.String field,long value) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.hincrBy(key,field,value);
    }
    
    public static java.lang.Long hincrBy(byte[] key,byte[] field,long value) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.hincrBy(key,field,value);
    }
    
    public static java.util.Set hkeys(byte[] key) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.hkeys(key);
    }
    
    public static java.util.Set hkeys(java.lang.String key) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.hkeys(key);
    }
    
    public static java.lang.Long hlen(java.lang.String key) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.hlen(key);
    }
    
    public static java.lang.Long hlen(byte[] key) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.hlen(key);
    }
    
    public static java.util.List hmget(java.lang.String key,java.lang.String[] fields) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.hmget(key,fields);
    }
    
    public static java.util.List hmget(byte[] key,byte[][] fields) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.hmget(key,fields);
    }
    
    public static java.lang.String hmset(java.lang.String key,java.util.Map hash) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.hmset(key,hash);
    }
    
    public static java.lang.String hmset(byte[] key,java.util.Map hash) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.hmset(key,hash);
    }
    
    public static java.lang.Long hset(byte[] key,byte[] field,byte[] value) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.hset(key,field,value);
    }
    
    public static java.lang.Long hset(java.lang.String key,java.lang.String field,java.lang.String value) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.hset(key,field,value);
    }
    
    public static java.lang.Long hsetnx(java.lang.String key,java.lang.String field,java.lang.String value) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.hsetnx(key,field,value);
    }
    
    public static java.lang.Long hsetnx(byte[] key,byte[] field,byte[] value) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.hsetnx(key,field,value);
    }
    
    public static java.util.Collection hvals(byte[] x0) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(x0);
        return jedis.hvals(x0);
    }
    
    public static java.util.List hvals(java.lang.String key) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.hvals(key);
    }
    
    public static java.lang.Long incr(byte[] key) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.incr(key);
    }
    
    public static java.lang.Long incr(java.lang.String key) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.incr(key);
    }
    
    public static java.lang.Long incrBy(java.lang.String key,long integer) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.incrBy(key,integer);
    }
    
    public static java.lang.Long incrBy(byte[] key,long integer) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.incrBy(key,integer);
    }
    
    public static java.lang.String info() {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.info();
        } else {
            throw new JedisConnectionException("Cannot execute info with sharded instance.");
        }
    }
    
    public static boolean isConnected() {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.isConnected();
        } else {
            throw new JedisConnectionException("Cannot execute isConnected with sharded instance.");
        }
    }
    
    public static java.util.Set keys(byte[] pattern) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.keys(pattern);
        } else {
            throw new JedisConnectionException("Cannot execute keys with sharded instance.");
        }
    }
    
    public static java.util.Set keys(java.lang.String pattern) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.keys(pattern);
        } else {
            throw new JedisConnectionException("Cannot execute keys with sharded instance.");
        }
    }
    
    public static java.lang.Long lastsave() {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.lastsave();
        } else {
            throw new JedisConnectionException("Cannot execute lastsave with sharded instance.");
        }
    }
    
    public static byte[] lindex(byte[] key,int index) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.lindex(key,index);
    }
    
    public static java.lang.String lindex(java.lang.String key,long index) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.lindex(key,index);
    }
    
    public static java.lang.Long linsert(byte[] key,redis.clients.jedis.BinaryClient.LIST_POSITION where,byte[] pivot,byte[] value) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.linsert(key,where,pivot,value);
    }
    
    public static java.lang.Long linsert(java.lang.String key,redis.clients.jedis.BinaryClient.LIST_POSITION where,java.lang.String pivot,java.lang.String value) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.linsert(key,where,pivot,value);
    }
    
    public static java.lang.Long llen(byte[] key) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.llen(key);
    }
    
    public static java.lang.Long llen(java.lang.String key) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.llen(key);
    }
    
    public static java.lang.String lpop(java.lang.String key) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.lpop(key);
    }
    
    public static byte[] lpop(byte[] key) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.lpop(key);
    }
    
    public static java.lang.Long lpush(java.lang.String key,java.lang.String string) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.lpush(key,string);
    }
    
    public static java.lang.Long lpush(byte[] key,byte[] string) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.lpush(key,string);
    }
    
    public static java.lang.Long lpushx(java.lang.String key,java.lang.String string) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.lpushx(key,string);
        } else {
            throw new JedisConnectionException("Cannot execute lpushx with sharded instance.");
        }
    }
    
    public static java.lang.Long lpushx(byte[] key,byte[] string) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.lpushx(key,string);
        } else {
            throw new JedisConnectionException("Cannot execute lpushx with sharded instance.");
        }
    }
    
    public static java.util.List lrange(java.lang.String key,long start,long end) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.lrange(key,start,end);
    }
    
    public static java.util.List lrange(byte[] key,int start,int end) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.lrange(key,start,end);
    }
    
    public static java.lang.Long lrem(java.lang.String key,long count,java.lang.String value) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.lrem(key,count,value);
    }
    
    public static java.lang.Long lrem(byte[] key,int count,byte[] value) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.lrem(key,count,value);
    }
    
    public static java.lang.String lset(java.lang.String key,long index,java.lang.String value) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.lset(key,index,value);
    }
    
    public static java.lang.String lset(byte[] key,int index,byte[] value) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.lset(key,index,value);
    }
    
    public static java.lang.String ltrim(java.lang.String key,long start,long end) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.ltrim(key,start,end);
    }
    
    public static java.lang.String ltrim(byte[] key,int start,int end) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.ltrim(key,start,end);
    }
    
    public static java.util.List mget(byte[][] keys) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.mget(keys);
        } else {
            throw new JedisConnectionException("Cannot execute mget with sharded instance.");
        }
    }
    
    public static java.util.List mget(java.lang.String[] keys) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.mget(keys);
        } else {
            throw new JedisConnectionException("Cannot execute mget with sharded instance.");
        }
    }
    
    public static void monitor(redis.clients.jedis.JedisMonitor jedisMonitor) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            jedis.monitor(jedisMonitor);
        } else {
            throw new JedisConnectionException("Cannot execute monitor with sharded instance.");
        }
    }
    
    public static java.lang.Long move(java.lang.String key,int dbIndex) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.move(key,dbIndex);
        } else {
            throw new JedisConnectionException("Cannot execute move with sharded instance.");
        }
    }
    
    public static java.lang.Long move(byte[] key,int dbIndex) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.move(key,dbIndex);
        } else {
            throw new JedisConnectionException("Cannot execute move with sharded instance.");
        }
    }
    
    public static java.lang.String mset(java.lang.String[] keysvalues) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.mset(keysvalues);
        } else {
            throw new JedisConnectionException("Cannot execute mset with sharded instance.");
        }
    }
    
    public static java.lang.String mset(byte[][] keysvalues) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.mset(keysvalues);
        } else {
            throw new JedisConnectionException("Cannot execute mset with sharded instance.");
        }
    }
    
    public static java.lang.Long msetnx(java.lang.String[] keysvalues) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.msetnx(keysvalues);
        } else {
            throw new JedisConnectionException("Cannot execute msetnx with sharded instance.");
        }
    }
    
    public static java.lang.Long msetnx(byte[][] keysvalues) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.msetnx(keysvalues);
        } else {
            throw new JedisConnectionException("Cannot execute msetnx with sharded instance.");
        }
    }
    
    public static redis.clients.jedis.Transaction multi() {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.multi();
        } else {
            throw new JedisConnectionException("Cannot execute multi with sharded instance.");
        }
    }
    
    public static java.util.List multi(redis.clients.jedis.TransactionBlock jedisTransaction) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.multi(jedisTransaction);
        } else {
            throw new JedisConnectionException("Cannot execute multi with sharded instance.");
        }
    }
    
    public static java.lang.Long persist(byte[] key) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.persist(key);
        } else {
            throw new JedisConnectionException("Cannot execute persist with sharded instance.");
        }
    }
    
    public static java.lang.Long persist(java.lang.String key) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.persist(key);
        } else {
            throw new JedisConnectionException("Cannot execute persist with sharded instance.");
        }
    }
    
    public static java.lang.String ping() {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.ping();
        } else {
            throw new JedisConnectionException("Cannot execute ping with sharded instance.");
        }
    }
    
    public static redis.clients.jedis.Pipeline pipelined() {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.pipelined();
        } else {
            throw new JedisConnectionException("Cannot execute pipelined with sharded instance.");
        }
    }
    
    public static java.util.List pipelined(redis.clients.jedis.PipelineBlock jedisPipeline) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.pipelined(jedisPipeline);
        } else {
            throw new JedisConnectionException("Cannot execute pipelined with sharded instance.");
        }
    }
    
    public static void psubscribe(redis.clients.jedis.BinaryJedisPubSub jedisPubSub,byte[][] patterns) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            jedis.psubscribe(jedisPubSub,patterns);
        } else {
            throw new JedisConnectionException("Cannot execute psubscribe with sharded instance.");
        }
    }
    
    public static void psubscribe(redis.clients.jedis.JedisPubSub jedisPubSub,java.lang.String[] patterns) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            jedis.psubscribe(jedisPubSub,patterns);
        } else {
            throw new JedisConnectionException("Cannot execute psubscribe with sharded instance.");
        }
    }
    
    public static java.lang.Long publish(byte[] channel,byte[] message) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.publish(channel,message);
        } else {
            throw new JedisConnectionException("Cannot execute publish with sharded instance.");
        }
    }
    
    public static java.lang.Long publish(java.lang.String channel,java.lang.String message) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.publish(channel,message);
        } else {
            throw new JedisConnectionException("Cannot execute publish with sharded instance.");
        }
    }
    
    public static byte[] randomBinaryKey() {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.randomBinaryKey();
        } else {
            throw new JedisConnectionException("Cannot execute randomBinaryKey with sharded instance.");
        }
    }
    
    public static java.lang.String randomKey() {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.randomKey();
        } else {
            throw new JedisConnectionException("Cannot execute randomKey with sharded instance.");
        }
    }
    
    public static java.lang.String rename(java.lang.String oldkey,java.lang.String newkey) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.rename(oldkey,newkey);
        } else {
            throw new JedisConnectionException("Cannot execute rename with sharded instance.");
        }
    }
    
    public static java.lang.String rename(byte[] oldkey,byte[] newkey) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.rename(oldkey,newkey);
        } else {
            throw new JedisConnectionException("Cannot execute rename with sharded instance.");
        }
    }
    
    public static java.lang.Long renamenx(byte[] oldkey,byte[] newkey) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.renamenx(oldkey,newkey);
        } else {
            throw new JedisConnectionException("Cannot execute renamenx with sharded instance.");
        }
    }
    
    public static java.lang.Long renamenx(java.lang.String oldkey,java.lang.String newkey) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.renamenx(oldkey,newkey);
        } else {
            throw new JedisConnectionException("Cannot execute renamenx with sharded instance.");
        }
    }
    
    public static java.lang.String rpop(java.lang.String key) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.rpop(key);
    }
    
    public static byte[] rpop(byte[] key) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.rpop(key);
    }
    
    public static java.lang.String rpoplpush(java.lang.String srckey,java.lang.String dstkey) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.rpoplpush(srckey,dstkey);
        } else {
            throw new JedisConnectionException("Cannot execute rpoplpush with sharded instance.");
        }
    }
    
    public static byte[] rpoplpush(byte[] srckey,byte[] dstkey) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.rpoplpush(srckey,dstkey);
        } else {
            throw new JedisConnectionException("Cannot execute rpoplpush with sharded instance.");
        }
    }
    
    public static java.lang.Long rpush(byte[] key,byte[] string) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.rpush(key,string);
    }
    
    public static java.lang.Long rpush(java.lang.String key,java.lang.String string) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.rpush(key,string);
    }
    
    public static java.lang.Long rpushx(java.lang.String key,java.lang.String string) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.rpushx(key,string);
        } else {
            throw new JedisConnectionException("Cannot execute rpushx with sharded instance.");
        }
    }
    
    public static java.lang.Long rpushx(byte[] key,byte[] string) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.rpushx(key,string);
        } else {
            throw new JedisConnectionException("Cannot execute rpushx with sharded instance.");
        }
    }
    
    public static java.lang.Long sadd(java.lang.String key,java.lang.String member) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.sadd(key,member);
    }
    
    public static java.lang.Long sadd(byte[] key,byte[] member) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.sadd(key,member);
    }
    
    public static java.lang.String save() {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.save();
        } else {
            throw new JedisConnectionException("Cannot execute save with sharded instance.");
        }
    }
    
    public static java.lang.Long scard(java.lang.String key) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.scard(key);
    }
    
    public static java.lang.Long scard(byte[] key) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.scard(key);
    }
    
    public static java.util.Set sdiff(byte[][] keys) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.sdiff(keys);
        } else {
            throw new JedisConnectionException("Cannot execute sdiff with sharded instance.");
        }
    }
    
    public static java.util.Set sdiff(java.lang.String[] keys) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.sdiff(keys);
        } else {
            throw new JedisConnectionException("Cannot execute sdiff with sharded instance.");
        }
    }
    
    public static java.lang.Long sdiffstore(java.lang.String dstkey,java.lang.String[] keys) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.sdiffstore(dstkey,keys);
        } else {
            throw new JedisConnectionException("Cannot execute sdiffstore with sharded instance.");
        }
    }
    
    public static java.lang.Long sdiffstore(byte[] dstkey,byte[][] keys) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.sdiffstore(dstkey,keys);
        } else {
            throw new JedisConnectionException("Cannot execute sdiffstore with sharded instance.");
        }
    }
    
    public static java.lang.String select(int index) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.select(index);
        } else {
            throw new JedisConnectionException("Cannot execute select with sharded instance.");
        }
    }
    
    public static java.lang.String set(byte[] key,byte[] value) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.set(key,value);
    }
    
    public static java.lang.String set(java.lang.String key,java.lang.String value) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.set(key,value);
    }
    
    public static java.lang.Long setbit(byte[] key,long offset,byte[] value) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.setbit(key,offset,value);
        } else {
            throw new JedisConnectionException("Cannot execute setbit with sharded instance.");
        }
    }
    
    public static boolean setbit(java.lang.String key,long offset,boolean value) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.setbit(key,offset,value);
    }
    
    public static java.lang.String setex(java.lang.String key,int seconds,java.lang.String value) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.setex(key,seconds,value);
    }
    
    public static java.lang.String setex(byte[] key,int seconds,byte[] value) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.setex(key,seconds,value);
    }
    
    public static java.lang.Long setnx(java.lang.String key,java.lang.String value) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.setnx(key,value);
    }
    
    public static java.lang.Long setnx(byte[] key,byte[] value) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.setnx(key,value);
    }
    
    public static long setrange(byte[] key,long offset,byte[] value) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.setrange(key,offset,value);
        } else {
            throw new JedisConnectionException("Cannot execute setrange with sharded instance.");
        }
    }
    
    public static long setrange(java.lang.String key,long offset,java.lang.String value) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.setrange(key,offset,value);
    }
    
    public static java.lang.String shutdown() {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.shutdown();
        } else {
            throw new JedisConnectionException("Cannot execute shutdown with sharded instance.");
        }
    }
    
    public static java.util.Set sinter(java.lang.String[] keys) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.sinter(keys);
        } else {
            throw new JedisConnectionException("Cannot execute sinter with sharded instance.");
        }
    }
    
    public static java.util.Set sinter(byte[][] keys) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.sinter(keys);
        } else {
            throw new JedisConnectionException("Cannot execute sinter with sharded instance.");
        }
    }
    
    public static java.lang.Long sinterstore(java.lang.String dstkey,java.lang.String[] keys) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.sinterstore(dstkey,keys);
        } else {
            throw new JedisConnectionException("Cannot execute sinterstore with sharded instance.");
        }
    }
    
    public static java.lang.Long sinterstore(byte[] dstkey,byte[][] keys) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.sinterstore(dstkey,keys);
        } else {
            throw new JedisConnectionException("Cannot execute sinterstore with sharded instance.");
        }
    }
    
    public static java.lang.Boolean sismember(java.lang.String key,java.lang.String member) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.sismember(key,member);
    }
    
    public static java.lang.Boolean sismember(byte[] key,byte[] member) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.sismember(key,member);
    }
    
    public static java.lang.String slaveof(java.lang.String host,int port) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.slaveof(host,port);
        } else {
            throw new JedisConnectionException("Cannot execute slaveof with sharded instance.");
        }
    }
    
    public static java.lang.String slaveofNoOne() {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.slaveofNoOne();
        } else {
            throw new JedisConnectionException("Cannot execute slaveofNoOne with sharded instance.");
        }
    }
    
    public static java.util.Set smembers(byte[] key) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.smembers(key);
    }
    
    public static java.util.Set smembers(java.lang.String key) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.smembers(key);
    }
    
    public static java.lang.Long smove(byte[] srckey,byte[] dstkey,byte[] member) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.smove(srckey,dstkey,member);
        } else {
            throw new JedisConnectionException("Cannot execute smove with sharded instance.");
        }
    }
    
    public static java.lang.Long smove(java.lang.String srckey,java.lang.String dstkey,java.lang.String member) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.smove(srckey,dstkey,member);
        } else {
            throw new JedisConnectionException("Cannot execute smove with sharded instance.");
        }
    }
    
    public static java.util.List sort(java.lang.String key) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.sort(key);
    }
    
    public static java.util.List sort(byte[] key) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.sort(key);
    }
    
    public static java.util.List sort(java.lang.String key,redis.clients.jedis.SortingParams sortingParameters) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.sort(key,sortingParameters);
    }
    
    public static java.lang.Long sort(java.lang.String key,java.lang.String dstkey) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.sort(key,dstkey);
        } else {
            throw new JedisConnectionException("Cannot execute sort with sharded instance.");
        }
    }
    
    public static java.util.List sort(byte[] key,redis.clients.jedis.SortingParams sortingParameters) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.sort(key,sortingParameters);
    }
    
    public static java.lang.Long sort(byte[] key,byte[] dstkey) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.sort(key,dstkey);
        } else {
            throw new JedisConnectionException("Cannot execute sort with sharded instance.");
        }
    }
    
    public static java.lang.Long sort(java.lang.String key,redis.clients.jedis.SortingParams sortingParameters,java.lang.String dstkey) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.sort(key,sortingParameters,dstkey);
        } else {
            throw new JedisConnectionException("Cannot execute sort with sharded instance.");
        }
    }
    
    public static java.lang.Long sort(byte[] key,redis.clients.jedis.SortingParams sortingParameters,byte[] dstkey) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.sort(key,sortingParameters,dstkey);
        } else {
            throw new JedisConnectionException("Cannot execute sort with sharded instance.");
        }
    }
    
    public static java.lang.String spop(java.lang.String key) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.spop(key);
    }
    
    public static byte[] spop(byte[] key) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.spop(key);
    }
    
    public static java.lang.String srandmember(java.lang.String key) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.srandmember(key);
    }
    
    public static byte[] srandmember(byte[] key) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.srandmember(key);
    }
    
    public static java.lang.Long srem(java.lang.String key,java.lang.String member) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.srem(key,member);
    }
    
    public static java.lang.Long srem(byte[] key,byte[] member) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.srem(key,member);
    }
    
    public static java.lang.Long strlen(java.lang.String key) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.strlen(key);
        } else {
            throw new JedisConnectionException("Cannot execute strlen with sharded instance.");
        }
    }
    
    public static java.lang.Long strlen(byte[] key) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.strlen(key);
        } else {
            throw new JedisConnectionException("Cannot execute strlen with sharded instance.");
        }
    }
    
    public static void subscribe(redis.clients.jedis.BinaryJedisPubSub jedisPubSub,byte[][] channels) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            jedis.subscribe(jedisPubSub,channels);
        } else {
            throw new JedisConnectionException("Cannot execute subscribe with sharded instance.");
        }
    }
    
    public static void subscribe(redis.clients.jedis.JedisPubSub jedisPubSub,java.lang.String[] channels) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            jedis.subscribe(jedisPubSub,channels);
        } else {
            throw new JedisConnectionException("Cannot execute subscribe with sharded instance.");
        }
    }
    
    public static java.lang.String substr(java.lang.String key,int start,int end) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.substr(key,start,end);
    }
    
    public static byte[] substr(byte[] key,int start,int end) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.substr(key,start,end);
    }
    
    public static java.util.Set sunion(java.lang.String[] keys) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.sunion(keys);
        } else {
            throw new JedisConnectionException("Cannot execute sunion with sharded instance.");
        }
    }
    
    public static java.util.Set sunion(byte[][] keys) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.sunion(keys);
        } else {
            throw new JedisConnectionException("Cannot execute sunion with sharded instance.");
        }
    }
    
    public static java.lang.Long sunionstore(java.lang.String dstkey,java.lang.String[] keys) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.sunionstore(dstkey,keys);
        } else {
            throw new JedisConnectionException("Cannot execute sunionstore with sharded instance.");
        }
    }
    
    public static java.lang.Long sunionstore(byte[] dstkey,byte[][] keys) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.sunionstore(dstkey,keys);
        } else {
            throw new JedisConnectionException("Cannot execute sunionstore with sharded instance.");
        }
    }
    
    public static void sync() {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            jedis.sync();
        } else {
            throw new JedisConnectionException("Cannot execute sync with sharded instance.");
        }
    }
    
    public static java.lang.Long ttl(byte[] key) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.ttl(key);
    }
    
    public static java.lang.Long ttl(java.lang.String key) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.ttl(key);
    }
    
    public static java.lang.String type(byte[] key) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.type(key);
    }
    
    public static java.lang.String type(java.lang.String key) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.type(key);
    }
    
    public static java.lang.String unwatch() {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.unwatch();
        } else {
            throw new JedisConnectionException("Cannot execute unwatch with sharded instance.");
        }
    }
    
    public static java.lang.String watch(byte[][] keys) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.watch(keys);
        } else {
            throw new JedisConnectionException("Cannot execute watch with sharded instance.");
        }
    }
    
    public static java.lang.String watch(java.lang.String[] keys) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.watch(keys);
        } else {
            throw new JedisConnectionException("Cannot execute watch with sharded instance.");
        }
    }
    
    public static java.lang.Long zadd(byte[] key,double score,byte[] member) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zadd(key,score,member);
    }
    
    public static java.lang.Long zadd(java.lang.String key,double score,java.lang.String member) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zadd(key,score,member);
    }
    
    public static java.lang.Long zcard(byte[] key) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zcard(key);
    }
    
    public static java.lang.Long zcard(java.lang.String key) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zcard(key);
    }
    
    public static java.lang.Long zcount(java.lang.String key,double min,double max) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zcount(key,min,max);
    }
    
    public static java.lang.Long zcount(byte[] key,double min,double max) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zcount(key,min,max);
    }
    
    public static java.lang.Double zincrby(java.lang.String key,double score,java.lang.String member) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zincrby(key,score,member);
    }
    
    public static java.lang.Double zincrby(byte[] key,double score,byte[] member) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zincrby(key,score,member);
    }
    
    public static java.lang.Long zinterstore(byte[] dstkey,byte[][] sets) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.zinterstore(dstkey,sets);
        } else {
            throw new JedisConnectionException("Cannot execute zinterstore with sharded instance.");
        }
    }
    
    public static java.lang.Long zinterstore(java.lang.String dstkey,java.lang.String[] sets) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.zinterstore(dstkey,sets);
        } else {
            throw new JedisConnectionException("Cannot execute zinterstore with sharded instance.");
        }
    }
    
    public static java.lang.Long zinterstore(java.lang.String dstkey,redis.clients.jedis.ZParams params,java.lang.String[] sets) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.zinterstore(dstkey,params,sets);
        } else {
            throw new JedisConnectionException("Cannot execute zinterstore with sharded instance.");
        }
    }
    
    public static java.lang.Long zinterstore(byte[] dstkey,redis.clients.jedis.ZParams params,byte[][] sets) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.zinterstore(dstkey,params,sets);
        } else {
            throw new JedisConnectionException("Cannot execute zinterstore with sharded instance.");
        }
    }
    
    public static java.util.Set zrange(byte[] key,int start,int end) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zrange(key,start,end);
    }
    
    public static java.util.Set zrange(java.lang.String key,int start,int end) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zrange(key,start,end);
    }
    
    public static java.util.Set zrangeByScore(java.lang.String key,java.lang.String min,java.lang.String max) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.zrangeByScore(key,min,max);
        } else {
            throw new JedisConnectionException("Cannot execute zrangeByScore with sharded instance.");
        }
    }
    
    public static java.util.Set zrangeByScore(byte[] key,double min,double max) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zrangeByScore(key,min,max);
    }
    
    public static java.util.Set zrangeByScore(java.lang.String key,double min,double max) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zrangeByScore(key,min,max);
    }
    
    public static java.util.Set zrangeByScore(byte[] key,byte[] min,byte[] max) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.zrangeByScore(key,min,max);
        } else {
            throw new JedisConnectionException("Cannot execute zrangeByScore with sharded instance.");
        }
    }
    
    public static java.util.Set zrangeByScore(byte[] key,double min,double max,int offset,int count) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zrangeByScore(key,min,max,offset,count);
    }
    
    public static java.util.Set zrangeByScore(java.lang.String key,double min,double max,int offset,int count) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zrangeByScore(key,min,max,offset,count);
    }
    
    public static java.util.Set zrangeByScoreWithScores(byte[] key,double min,double max) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zrangeByScoreWithScores(key,min,max);
    }
    
    public static java.util.Set zrangeByScoreWithScores(java.lang.String key,double min,double max) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zrangeByScoreWithScores(key,min,max);
    }
    
    public static java.util.Set zrangeByScoreWithScores(java.lang.String key,double min,double max,int offset,int count) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zrangeByScoreWithScores(key,min,max,offset,count);
    }
    
    public static java.util.Set zrangeByScoreWithScores(byte[] key,double min,double max,int offset,int count) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zrangeByScoreWithScores(key,min,max,offset,count);
    }
    
    public static java.util.Set zrangeWithScores(java.lang.String key,int start,int end) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zrangeWithScores(key,start,end);
    }
    
    public static java.util.Set zrangeWithScores(byte[] key,int start,int end) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zrangeWithScores(key,start,end);
    }
    
    public static java.lang.Long zrank(byte[] key,byte[] member) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zrank(key,member);
    }
    
    public static java.lang.Long zrank(java.lang.String key,java.lang.String member) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zrank(key,member);
    }
    
    public static java.lang.Long zrem(java.lang.String key,java.lang.String member) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zrem(key,member);
    }
    
    public static java.lang.Long zrem(byte[] key,byte[] member) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zrem(key,member);
    }
    
    public static java.lang.Long zremrangeByRank(java.lang.String key,int start,int end) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zremrangeByRank(key,start,end);
    }
    
    public static java.lang.Long zremrangeByRank(byte[] key,int start,int end) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zremrangeByRank(key,start,end);
    }
    
    public static java.lang.Long zremrangeByScore(java.lang.String key,double start,double end) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zremrangeByScore(key,start,end);
    }
    
    public static java.lang.Long zremrangeByScore(byte[] key,double start,double end) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zremrangeByScore(key,start,end);
    }
    
    public static java.util.Set zrevrange(java.lang.String key,int start,int end) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zrevrange(key,start,end);
    }
    
    public static java.util.Set zrevrange(byte[] key,int start,int end) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zrevrange(key,start,end);
    }
    
    public static java.util.Set zrevrangeByScore(java.lang.String key,java.lang.String max,java.lang.String min) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.zrevrangeByScore(key,max,min);
        } else {
            throw new JedisConnectionException("Cannot execute zrevrangeByScore with sharded instance.");
        }
    }
    
    public static java.util.Set zrevrangeByScore(byte[] key,byte[] max,byte[] min) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.zrevrangeByScore(key,max,min);
        } else {
            throw new JedisConnectionException("Cannot execute zrevrangeByScore with sharded instance.");
        }
    }
    
    public static java.util.Set zrevrangeByScore(java.lang.String key,double max,double min) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zrevrangeByScore(key,max,min);
    }
    
    public static java.util.Set zrevrangeByScore(byte[] key,double max,double min) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zrevrangeByScore(key,max,min);
    }
    
    public static java.util.Set zrevrangeByScore(java.lang.String key,double max,double min,int offset,int count) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zrevrangeByScore(key,max,min,offset,count);
    }
    
    public static java.util.Set zrevrangeByScore(byte[] key,double max,double min,int offset,int count) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zrevrangeByScore(key,max,min,offset,count);
    }
    
    public static java.util.Set zrevrangeByScoreWithScores(java.lang.String key,double max,double min) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zrevrangeByScoreWithScores(key,max,min);
    }
    
    public static java.util.Set zrevrangeByScoreWithScores(byte[] key,double max,double min) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zrevrangeByScoreWithScores(key,max,min);
    }
    
    public static java.util.Set zrevrangeByScoreWithScores(java.lang.String key,double max,double min,int offset,int count) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zrevrangeByScoreWithScores(key,max,min,offset,count);
    }
    
    public static java.util.Set zrevrangeByScoreWithScores(byte[] key,double max,double min,int offset,int count) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zrevrangeByScoreWithScores(key,max,min,offset,count);
    }
    
    public static java.util.Set zrevrangeWithScores(java.lang.String key,int start,int end) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zrevrangeWithScores(key,start,end);
    }
    
    public static java.util.Set zrevrangeWithScores(byte[] key,int start,int end) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zrevrangeWithScores(key,start,end);
    }
    
    public static java.lang.Long zrevrank(byte[] key,byte[] member) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zrevrank(key,member);
    }
    
    public static java.lang.Long zrevrank(java.lang.String key,java.lang.String member) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zrevrank(key,member);
    }
    
    public static java.lang.Double zscore(java.lang.String key,java.lang.String member) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zscore(key,member);
    }
    
    public static java.lang.Double zscore(byte[] key,byte[] member) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(key);
        return jedis.zscore(key,member);
    }
    
    public static java.lang.Long zunionstore(byte[] dstkey,byte[][] sets) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.zunionstore(dstkey,sets);
        } else {
            throw new JedisConnectionException("Cannot execute zunionstore with sharded instance.");
        }
    }
    
    public static java.lang.Long zunionstore(java.lang.String dstkey,java.lang.String[] sets) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.zunionstore(dstkey,sets);
        } else {
            throw new JedisConnectionException("Cannot execute zunionstore with sharded instance.");
        }
    }
    
    public static java.lang.Long zunionstore(java.lang.String dstkey,redis.clients.jedis.ZParams params,java.lang.String[] sets) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.zunionstore(dstkey,params,sets);
        } else {
            throw new JedisConnectionException("Cannot execute zunionstore with sharded instance.");
        }
    }
    
    public static java.lang.Long zunionstore(byte[] dstkey,redis.clients.jedis.ZParams params,byte[][] sets) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.zunionstore(dstkey,params,sets);
        } else {
            throw new JedisConnectionException("Cannot execute zunionstore with sharded instance.");
        }
    }
    
}