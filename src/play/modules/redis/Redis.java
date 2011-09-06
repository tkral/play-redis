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


    public static java.lang.Long append(java.lang.String arg0,java.lang.String arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.append(arg0,arg1);
    }
    
    public static java.lang.Long append(byte[] arg0,byte[] arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.append(arg0,arg1);
    }
    
    public static java.lang.String auth(java.lang.String arg0) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.auth(arg0);
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
    
    public static java.util.List blpop(int arg0,java.lang.String[] arg1) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.blpop(arg0,arg1);
        } else {
            throw new JedisConnectionException("Cannot execute blpop with sharded instance.");
        }
    }
    
    public static java.util.List blpop(int arg0,byte[][] arg1) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.blpop(arg0,arg1);
        } else {
            throw new JedisConnectionException("Cannot execute blpop with sharded instance.");
        }
    }
    
    public static java.util.List brpop(int arg0,java.lang.String[] arg1) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.brpop(arg0,arg1);
        } else {
            throw new JedisConnectionException("Cannot execute brpop with sharded instance.");
        }
    }
    
    public static java.util.List brpop(int arg0,byte[][] arg1) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.brpop(arg0,arg1);
        } else {
            throw new JedisConnectionException("Cannot execute brpop with sharded instance.");
        }
    }
    
    public static byte[] brpoplpush(byte[] arg0,byte[] arg1,int arg2) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.brpoplpush(arg0,arg1,arg2);
        } else {
            throw new JedisConnectionException("Cannot execute brpoplpush with sharded instance.");
        }
    }
    
    public static java.lang.String brpoplpush(java.lang.String arg0,java.lang.String arg1,int arg2) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.brpoplpush(arg0,arg1,arg2);
        } else {
            throw new JedisConnectionException("Cannot execute brpoplpush with sharded instance.");
        }
    }
    
    public static java.util.List configGet(java.lang.String arg0) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.configGet(arg0);
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
    
    public static java.lang.String configSet(java.lang.String arg0,java.lang.String arg1) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.configSet(arg0,arg1);
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
    
    public static java.lang.String debug(redis.clients.jedis.DebugParams arg0) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.debug(arg0);
        } else {
            throw new JedisConnectionException("Cannot execute debug with sharded instance.");
        }
    }
    
    public static java.lang.Long decr(byte[] arg0) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.decr(arg0);
    }
    
    public static java.lang.Long decr(java.lang.String arg0) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.decr(arg0);
    }
    
    public static java.lang.Long decrBy(byte[] arg0,long arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.decrBy(arg0,arg1);
    }
    
    public static java.lang.Long decrBy(java.lang.String arg0,long arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.decrBy(arg0,arg1);
    }
    
    public static java.lang.Long del(java.lang.String[] arg0) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.del(arg0);
        } else {
            throw new JedisConnectionException("Cannot execute del with sharded instance.");
        }
    }
    
    public static java.lang.Long del(byte[][] arg0) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.del(arg0);
        } else {
            throw new JedisConnectionException("Cannot execute del with sharded instance.");
        }
    }
    
    public static byte[] echo(byte[] arg0) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.echo(arg0);
        } else {
            throw new JedisConnectionException("Cannot execute echo with sharded instance.");
        }
    }
    
    public static java.lang.String echo(java.lang.String arg0) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.echo(arg0);
        } else {
            throw new JedisConnectionException("Cannot execute echo with sharded instance.");
        }
    }
    
    public static java.lang.Boolean exists(java.lang.String arg0) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.exists(arg0);
    }
    
    public static java.lang.Boolean exists(byte[] arg0) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.exists(arg0);
    }
    
    public static java.lang.Long expire(java.lang.String arg0,int arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.expire(arg0,arg1);
    }
    
    public static java.lang.Long expire(byte[] arg0,int arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.expire(arg0,arg1);
    }
    
    public static java.lang.Long expireAt(byte[] arg0,long arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.expireAt(arg0,arg1);
    }
    
    public static java.lang.Long expireAt(java.lang.String arg0,long arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.expireAt(arg0,arg1);
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
    
    public static java.lang.String get(java.lang.String arg0) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.get(arg0);
    }
    
    public static byte[] get(byte[] arg0) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.get(arg0);
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
    
    public static byte[] getSet(byte[] arg0,byte[] arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.getSet(arg0,arg1);
    }
    
    public static java.lang.String getSet(java.lang.String arg0,java.lang.String arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.getSet(arg0,arg1);
    }
    
    public static boolean getbit(java.lang.String arg0,long arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.getbit(arg0,arg1);
    }
    
    public static java.lang.Long getbit(byte[] arg0,long arg1) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.getbit(arg0,arg1);
        } else {
            throw new JedisConnectionException("Cannot execute getbit with sharded instance.");
        }
    }
    
    public static java.lang.String getrange(java.lang.String arg0,long arg1,long arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.getrange(arg0,arg1,arg2);
    }
    
    public static java.lang.String getrange(byte[] arg0,long arg1,long arg2) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.getrange(arg0,arg1,arg2);
        } else {
            throw new JedisConnectionException("Cannot execute getrange with sharded instance.");
        }
    }
    
    public static java.lang.Long hdel(byte[] arg0,byte[] arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.hdel(arg0,arg1);
    }
    
    public static java.lang.Long hdel(java.lang.String arg0,java.lang.String arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.hdel(arg0,arg1);
    }
    
    public static java.lang.Boolean hexists(java.lang.String arg0,java.lang.String arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.hexists(arg0,arg1);
    }
    
    public static java.lang.Boolean hexists(byte[] arg0,byte[] arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.hexists(arg0,arg1);
    }
    
    public static byte[] hget(byte[] arg0,byte[] arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.hget(arg0,arg1);
    }
    
    public static java.lang.String hget(java.lang.String arg0,java.lang.String arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.hget(arg0,arg1);
    }
    
    public static java.util.Map hgetAll(java.lang.String arg0) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.hgetAll(arg0);
    }
    
    public static java.util.Map hgetAll(byte[] arg0) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.hgetAll(arg0);
    }
    
    public static java.lang.Long hincrBy(java.lang.String arg0,java.lang.String arg1,long arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.hincrBy(arg0,arg1,arg2);
    }
    
    public static java.lang.Long hincrBy(byte[] arg0,byte[] arg1,long arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.hincrBy(arg0,arg1,arg2);
    }
    
    public static java.util.Set hkeys(byte[] arg0) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.hkeys(arg0);
    }
    
    public static java.util.Set hkeys(java.lang.String arg0) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.hkeys(arg0);
    }
    
    public static java.lang.Long hlen(java.lang.String arg0) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.hlen(arg0);
    }
    
    public static java.lang.Long hlen(byte[] arg0) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.hlen(arg0);
    }
    
    public static java.util.List hmget(java.lang.String arg0,java.lang.String[] arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.hmget(arg0,arg1);
    }
    
    public static java.util.List hmget(byte[] arg0,byte[][] arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.hmget(arg0,arg1);
    }
    
    public static java.lang.String hmset(java.lang.String arg0,java.util.Map arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.hmset(arg0,arg1);
    }
    
    public static java.lang.String hmset(byte[] arg0,java.util.Map arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.hmset(arg0,arg1);
    }
    
    public static java.lang.Long hset(byte[] arg0,byte[] arg1,byte[] arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.hset(arg0,arg1,arg2);
    }
    
    public static java.lang.Long hset(java.lang.String arg0,java.lang.String arg1,java.lang.String arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.hset(arg0,arg1,arg2);
    }
    
    public static java.lang.Long hsetnx(java.lang.String arg0,java.lang.String arg1,java.lang.String arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.hsetnx(arg0,arg1,arg2);
    }
    
    public static java.lang.Long hsetnx(byte[] arg0,byte[] arg1,byte[] arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.hsetnx(arg0,arg1,arg2);
    }
    
    public static java.util.Collection hvals(byte[] arg0) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.hvals(arg0);
    }
    
    public static java.util.List hvals(java.lang.String arg0) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.hvals(arg0);
    }
    
    public static java.lang.Long incr(byte[] arg0) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.incr(arg0);
    }
    
    public static java.lang.Long incr(java.lang.String arg0) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.incr(arg0);
    }
    
    public static java.lang.Long incrBy(java.lang.String arg0,long arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.incrBy(arg0,arg1);
    }
    
    public static java.lang.Long incrBy(byte[] arg0,long arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.incrBy(arg0,arg1);
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
    
    public static java.util.Set keys(byte[] arg0) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.keys(arg0);
        } else {
            throw new JedisConnectionException("Cannot execute keys with sharded instance.");
        }
    }
    
    public static java.util.Set keys(java.lang.String arg0) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.keys(arg0);
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
    
    public static byte[] lindex(byte[] arg0,int arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.lindex(arg0,arg1);
    }
    
    public static java.lang.String lindex(java.lang.String arg0,long arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.lindex(arg0,arg1);
    }
    
    public static java.lang.Long linsert(byte[] arg0,redis.clients.jedis.BinaryClient.LIST_POSITION arg1,byte[] arg2,byte[] arg3) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.linsert(arg0,arg1,arg2,arg3);
    }
    
    public static java.lang.Long linsert(java.lang.String arg0,redis.clients.jedis.BinaryClient.LIST_POSITION arg1,java.lang.String arg2,java.lang.String arg3) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.linsert(arg0,arg1,arg2,arg3);
    }
    
    public static java.lang.Long llen(byte[] arg0) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.llen(arg0);
    }
    
    public static java.lang.Long llen(java.lang.String arg0) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.llen(arg0);
    }
    
    public static java.lang.String lpop(java.lang.String arg0) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.lpop(arg0);
    }
    
    public static byte[] lpop(byte[] arg0) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.lpop(arg0);
    }
    
    public static java.lang.Long lpush(java.lang.String arg0,java.lang.String arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.lpush(arg0,arg1);
    }
    
    public static java.lang.Long lpush(byte[] arg0,byte[] arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.lpush(arg0,arg1);
    }
    
    public static java.lang.Long lpushx(java.lang.String arg0,java.lang.String arg1) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.lpushx(arg0,arg1);
        } else {
            throw new JedisConnectionException("Cannot execute lpushx with sharded instance.");
        }
    }
    
    public static java.lang.Long lpushx(byte[] arg0,byte[] arg1) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.lpushx(arg0,arg1);
        } else {
            throw new JedisConnectionException("Cannot execute lpushx with sharded instance.");
        }
    }
    
    public static java.util.List lrange(java.lang.String arg0,long arg1,long arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.lrange(arg0,arg1,arg2);
    }
    
    public static java.util.List lrange(byte[] arg0,int arg1,int arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.lrange(arg0,arg1,arg2);
    }
    
    public static java.lang.Long lrem(java.lang.String arg0,long arg1,java.lang.String arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.lrem(arg0,arg1,arg2);
    }
    
    public static java.lang.Long lrem(byte[] arg0,int arg1,byte[] arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.lrem(arg0,arg1,arg2);
    }
    
    public static java.lang.String lset(java.lang.String arg0,long arg1,java.lang.String arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.lset(arg0,arg1,arg2);
    }
    
    public static java.lang.String lset(byte[] arg0,int arg1,byte[] arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.lset(arg0,arg1,arg2);
    }
    
    public static java.lang.String ltrim(java.lang.String arg0,long arg1,long arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.ltrim(arg0,arg1,arg2);
    }
    
    public static java.lang.String ltrim(byte[] arg0,int arg1,int arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.ltrim(arg0,arg1,arg2);
    }
    
    public static java.util.List mget(byte[][] arg0) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.mget(arg0);
        } else {
            throw new JedisConnectionException("Cannot execute mget with sharded instance.");
        }
    }
    
    public static java.util.List mget(java.lang.String[] arg0) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.mget(arg0);
        } else {
            throw new JedisConnectionException("Cannot execute mget with sharded instance.");
        }
    }
    
    public static void monitor(redis.clients.jedis.JedisMonitor arg0) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            jedis.monitor(arg0);
        } else {
            throw new JedisConnectionException("Cannot execute monitor with sharded instance.");
        }
    }
    
    public static java.lang.Long move(java.lang.String arg0,int arg1) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.move(arg0,arg1);
        } else {
            throw new JedisConnectionException("Cannot execute move with sharded instance.");
        }
    }
    
    public static java.lang.Long move(byte[] arg0,int arg1) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.move(arg0,arg1);
        } else {
            throw new JedisConnectionException("Cannot execute move with sharded instance.");
        }
    }
    
    public static java.lang.String mset(java.lang.String[] arg0) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.mset(arg0);
        } else {
            throw new JedisConnectionException("Cannot execute mset with sharded instance.");
        }
    }
    
    public static java.lang.String mset(byte[][] arg0) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.mset(arg0);
        } else {
            throw new JedisConnectionException("Cannot execute mset with sharded instance.");
        }
    }
    
    public static java.lang.Long msetnx(java.lang.String[] arg0) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.msetnx(arg0);
        } else {
            throw new JedisConnectionException("Cannot execute msetnx with sharded instance.");
        }
    }
    
    public static java.lang.Long msetnx(byte[][] arg0) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.msetnx(arg0);
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
    
    public static java.util.List multi(redis.clients.jedis.TransactionBlock arg0) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.multi(arg0);
        } else {
            throw new JedisConnectionException("Cannot execute multi with sharded instance.");
        }
    }
    
    public static java.lang.Long persist(byte[] arg0) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.persist(arg0);
        } else {
            throw new JedisConnectionException("Cannot execute persist with sharded instance.");
        }
    }
    
    public static java.lang.Long persist(java.lang.String arg0) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.persist(arg0);
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
    
    public static java.util.List pipelined(redis.clients.jedis.PipelineBlock arg0) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.pipelined(arg0);
        } else {
            throw new JedisConnectionException("Cannot execute pipelined with sharded instance.");
        }
    }
    
    public static void psubscribe(redis.clients.jedis.BinaryJedisPubSub arg0,byte[][] arg1) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            jedis.psubscribe(arg0,arg1);
        } else {
            throw new JedisConnectionException("Cannot execute psubscribe with sharded instance.");
        }
    }
    
    public static void psubscribe(redis.clients.jedis.JedisPubSub arg0,java.lang.String[] arg1) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            jedis.psubscribe(arg0,arg1);
        } else {
            throw new JedisConnectionException("Cannot execute psubscribe with sharded instance.");
        }
    }
    
    public static java.lang.Long publish(byte[] arg0,byte[] arg1) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.publish(arg0,arg1);
        } else {
            throw new JedisConnectionException("Cannot execute publish with sharded instance.");
        }
    }
    
    public static java.lang.Long publish(java.lang.String arg0,java.lang.String arg1) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.publish(arg0,arg1);
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
    
    public static java.lang.String rename(java.lang.String arg0,java.lang.String arg1) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.rename(arg0,arg1);
        } else {
            throw new JedisConnectionException("Cannot execute rename with sharded instance.");
        }
    }
    
    public static java.lang.String rename(byte[] arg0,byte[] arg1) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.rename(arg0,arg1);
        } else {
            throw new JedisConnectionException("Cannot execute rename with sharded instance.");
        }
    }
    
    public static java.lang.Long renamenx(byte[] arg0,byte[] arg1) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.renamenx(arg0,arg1);
        } else {
            throw new JedisConnectionException("Cannot execute renamenx with sharded instance.");
        }
    }
    
    public static java.lang.Long renamenx(java.lang.String arg0,java.lang.String arg1) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.renamenx(arg0,arg1);
        } else {
            throw new JedisConnectionException("Cannot execute renamenx with sharded instance.");
        }
    }
    
    public static java.lang.String rpop(java.lang.String arg0) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.rpop(arg0);
    }
    
    public static byte[] rpop(byte[] arg0) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.rpop(arg0);
    }
    
    public static java.lang.String rpoplpush(java.lang.String arg0,java.lang.String arg1) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.rpoplpush(arg0,arg1);
        } else {
            throw new JedisConnectionException("Cannot execute rpoplpush with sharded instance.");
        }
    }
    
    public static byte[] rpoplpush(byte[] arg0,byte[] arg1) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.rpoplpush(arg0,arg1);
        } else {
            throw new JedisConnectionException("Cannot execute rpoplpush with sharded instance.");
        }
    }
    
    public static java.lang.Long rpush(byte[] arg0,byte[] arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.rpush(arg0,arg1);
    }
    
    public static java.lang.Long rpush(java.lang.String arg0,java.lang.String arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.rpush(arg0,arg1);
    }
    
    public static java.lang.Long rpushx(java.lang.String arg0,java.lang.String arg1) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.rpushx(arg0,arg1);
        } else {
            throw new JedisConnectionException("Cannot execute rpushx with sharded instance.");
        }
    }
    
    public static java.lang.Long rpushx(byte[] arg0,byte[] arg1) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.rpushx(arg0,arg1);
        } else {
            throw new JedisConnectionException("Cannot execute rpushx with sharded instance.");
        }
    }
    
    public static java.lang.Long sadd(java.lang.String arg0,java.lang.String arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.sadd(arg0,arg1);
    }
    
    public static java.lang.Long sadd(byte[] arg0,byte[] arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.sadd(arg0,arg1);
    }
    
    public static java.lang.String save() {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.save();
        } else {
            throw new JedisConnectionException("Cannot execute save with sharded instance.");
        }
    }
    
    public static java.lang.Long scard(java.lang.String arg0) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.scard(arg0);
    }
    
    public static java.lang.Long scard(byte[] arg0) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.scard(arg0);
    }
    
    public static java.util.Set sdiff(byte[][] arg0) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.sdiff(arg0);
        } else {
            throw new JedisConnectionException("Cannot execute sdiff with sharded instance.");
        }
    }
    
    public static java.util.Set sdiff(java.lang.String[] arg0) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.sdiff(arg0);
        } else {
            throw new JedisConnectionException("Cannot execute sdiff with sharded instance.");
        }
    }
    
    public static java.lang.Long sdiffstore(java.lang.String arg0,java.lang.String[] arg1) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.sdiffstore(arg0,arg1);
        } else {
            throw new JedisConnectionException("Cannot execute sdiffstore with sharded instance.");
        }
    }
    
    public static java.lang.Long sdiffstore(byte[] arg0,byte[][] arg1) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.sdiffstore(arg0,arg1);
        } else {
            throw new JedisConnectionException("Cannot execute sdiffstore with sharded instance.");
        }
    }
    
    public static java.lang.String select(int arg0) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.select(arg0);
        } else {
            throw new JedisConnectionException("Cannot execute select with sharded instance.");
        }
    }
    
    public static java.lang.String set(byte[] arg0,byte[] arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.set(arg0,arg1);
    }
    
    public static java.lang.String set(java.lang.String arg0,java.lang.String arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.set(arg0,arg1);
    }
    
    public static java.lang.Long setbit(byte[] arg0,long arg1,byte[] arg2) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.setbit(arg0,arg1,arg2);
        } else {
            throw new JedisConnectionException("Cannot execute setbit with sharded instance.");
        }
    }
    
    public static boolean setbit(java.lang.String arg0,long arg1,boolean arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.setbit(arg0,arg1,arg2);
    }
    
    public static java.lang.String setex(java.lang.String arg0,int arg1,java.lang.String arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.setex(arg0,arg1,arg2);
    }
    
    public static java.lang.String setex(byte[] arg0,int arg1,byte[] arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.setex(arg0,arg1,arg2);
    }
    
    public static java.lang.Long setnx(java.lang.String arg0,java.lang.String arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.setnx(arg0,arg1);
    }
    
    public static java.lang.Long setnx(byte[] arg0,byte[] arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.setnx(arg0,arg1);
    }
    
    public static long setrange(byte[] arg0,long arg1,byte[] arg2) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.setrange(arg0,arg1,arg2);
        } else {
            throw new JedisConnectionException("Cannot execute setrange with sharded instance.");
        }
    }
    
    public static long setrange(java.lang.String arg0,long arg1,java.lang.String arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.setrange(arg0,arg1,arg2);
    }
    
    public static java.lang.String shutdown() {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.shutdown();
        } else {
            throw new JedisConnectionException("Cannot execute shutdown with sharded instance.");
        }
    }
    
    public static java.util.Set sinter(java.lang.String[] arg0) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.sinter(arg0);
        } else {
            throw new JedisConnectionException("Cannot execute sinter with sharded instance.");
        }
    }
    
    public static java.util.Set sinter(byte[][] arg0) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.sinter(arg0);
        } else {
            throw new JedisConnectionException("Cannot execute sinter with sharded instance.");
        }
    }
    
    public static java.lang.Long sinterstore(java.lang.String arg0,java.lang.String[] arg1) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.sinterstore(arg0,arg1);
        } else {
            throw new JedisConnectionException("Cannot execute sinterstore with sharded instance.");
        }
    }
    
    public static java.lang.Long sinterstore(byte[] arg0,byte[][] arg1) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.sinterstore(arg0,arg1);
        } else {
            throw new JedisConnectionException("Cannot execute sinterstore with sharded instance.");
        }
    }
    
    public static java.lang.Boolean sismember(java.lang.String arg0,java.lang.String arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.sismember(arg0,arg1);
    }
    
    public static java.lang.Boolean sismember(byte[] arg0,byte[] arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.sismember(arg0,arg1);
    }
    
    public static java.lang.String slaveof(java.lang.String arg0,int arg1) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.slaveof(arg0,arg1);
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
    
    public static java.util.Set smembers(byte[] arg0) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.smembers(arg0);
    }
    
    public static java.util.Set smembers(java.lang.String arg0) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.smembers(arg0);
    }
    
    public static java.lang.Long smove(byte[] arg0,byte[] arg1,byte[] arg2) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.smove(arg0,arg1,arg2);
        } else {
            throw new JedisConnectionException("Cannot execute smove with sharded instance.");
        }
    }
    
    public static java.lang.Long smove(java.lang.String arg0,java.lang.String arg1,java.lang.String arg2) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.smove(arg0,arg1,arg2);
        } else {
            throw new JedisConnectionException("Cannot execute smove with sharded instance.");
        }
    }
    
    public static java.util.List sort(java.lang.String arg0) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.sort(arg0);
    }
    
    public static java.util.List sort(byte[] arg0) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.sort(arg0);
    }
    
    public static java.util.List sort(java.lang.String arg0,redis.clients.jedis.SortingParams arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.sort(arg0,arg1);
    }
    
    public static java.lang.Long sort(java.lang.String arg0,java.lang.String arg1) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.sort(arg0,arg1);
        } else {
            throw new JedisConnectionException("Cannot execute sort with sharded instance.");
        }
    }
    
    public static java.util.List sort(byte[] arg0,redis.clients.jedis.SortingParams arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.sort(arg0,arg1);
    }
    
    public static java.lang.Long sort(byte[] arg0,byte[] arg1) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.sort(arg0,arg1);
        } else {
            throw new JedisConnectionException("Cannot execute sort with sharded instance.");
        }
    }
    
    public static java.lang.Long sort(java.lang.String arg0,redis.clients.jedis.SortingParams arg1,java.lang.String arg2) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.sort(arg0,arg1,arg2);
        } else {
            throw new JedisConnectionException("Cannot execute sort with sharded instance.");
        }
    }
    
    public static java.lang.Long sort(byte[] arg0,redis.clients.jedis.SortingParams arg1,byte[] arg2) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.sort(arg0,arg1,arg2);
        } else {
            throw new JedisConnectionException("Cannot execute sort with sharded instance.");
        }
    }
    
    public static java.lang.String spop(java.lang.String arg0) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.spop(arg0);
    }
    
    public static byte[] spop(byte[] arg0) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.spop(arg0);
    }
    
    public static java.lang.String srandmember(java.lang.String arg0) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.srandmember(arg0);
    }
    
    public static byte[] srandmember(byte[] arg0) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.srandmember(arg0);
    }
    
    public static java.lang.Long srem(java.lang.String arg0,java.lang.String arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.srem(arg0,arg1);
    }
    
    public static java.lang.Long srem(byte[] arg0,byte[] arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.srem(arg0,arg1);
    }
    
    public static java.lang.Long strlen(java.lang.String arg0) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.strlen(arg0);
        } else {
            throw new JedisConnectionException("Cannot execute strlen with sharded instance.");
        }
    }
    
    public static java.lang.Long strlen(byte[] arg0) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.strlen(arg0);
        } else {
            throw new JedisConnectionException("Cannot execute strlen with sharded instance.");
        }
    }
    
    public static void subscribe(redis.clients.jedis.BinaryJedisPubSub arg0,byte[][] arg1) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            jedis.subscribe(arg0,arg1);
        } else {
            throw new JedisConnectionException("Cannot execute subscribe with sharded instance.");
        }
    }
    
    public static void subscribe(redis.clients.jedis.JedisPubSub arg0,java.lang.String[] arg1) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            jedis.subscribe(arg0,arg1);
        } else {
            throw new JedisConnectionException("Cannot execute subscribe with sharded instance.");
        }
    }
    
    public static java.lang.String substr(java.lang.String arg0,int arg1,int arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.substr(arg0,arg1,arg2);
    }
    
    public static byte[] substr(byte[] arg0,int arg1,int arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.substr(arg0,arg1,arg2);
    }
    
    public static java.util.Set sunion(java.lang.String[] arg0) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.sunion(arg0);
        } else {
            throw new JedisConnectionException("Cannot execute sunion with sharded instance.");
        }
    }
    
    public static java.util.Set sunion(byte[][] arg0) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.sunion(arg0);
        } else {
            throw new JedisConnectionException("Cannot execute sunion with sharded instance.");
        }
    }
    
    public static java.lang.Long sunionstore(java.lang.String arg0,java.lang.String[] arg1) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.sunionstore(arg0,arg1);
        } else {
            throw new JedisConnectionException("Cannot execute sunionstore with sharded instance.");
        }
    }
    
    public static java.lang.Long sunionstore(byte[] arg0,byte[][] arg1) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.sunionstore(arg0,arg1);
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
    
    public static java.lang.Long ttl(byte[] arg0) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.ttl(arg0);
    }
    
    public static java.lang.Long ttl(java.lang.String arg0) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.ttl(arg0);
    }
    
    public static java.lang.String type(byte[] arg0) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.type(arg0);
    }
    
    public static java.lang.String type(java.lang.String arg0) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.type(arg0);
    }
    
    public static java.lang.String unwatch() {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.unwatch();
        } else {
            throw new JedisConnectionException("Cannot execute unwatch with sharded instance.");
        }
    }
    
    public static java.lang.String watch(byte[][] arg0) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.watch(arg0);
        } else {
            throw new JedisConnectionException("Cannot execute watch with sharded instance.");
        }
    }
    
    public static java.lang.String watch(java.lang.String[] arg0) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.watch(arg0);
        } else {
            throw new JedisConnectionException("Cannot execute watch with sharded instance.");
        }
    }
    
    public static java.lang.Long zadd(byte[] arg0,double arg1,byte[] arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zadd(arg0,arg1,arg2);
    }
    
    public static java.lang.Long zadd(java.lang.String arg0,double arg1,java.lang.String arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zadd(arg0,arg1,arg2);
    }
    
    public static java.lang.Long zcard(byte[] arg0) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zcard(arg0);
    }
    
    public static java.lang.Long zcard(java.lang.String arg0) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zcard(arg0);
    }
    
    public static java.lang.Long zcount(java.lang.String arg0,double arg1,double arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zcount(arg0,arg1,arg2);
    }
    
    public static java.lang.Long zcount(byte[] arg0,double arg1,double arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zcount(arg0,arg1,arg2);
    }
    
    public static java.lang.Double zincrby(java.lang.String arg0,double arg1,java.lang.String arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zincrby(arg0,arg1,arg2);
    }
    
    public static java.lang.Double zincrby(byte[] arg0,double arg1,byte[] arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zincrby(arg0,arg1,arg2);
    }
    
    public static java.lang.Long zinterstore(byte[] arg0,byte[][] arg1) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.zinterstore(arg0,arg1);
        } else {
            throw new JedisConnectionException("Cannot execute zinterstore with sharded instance.");
        }
    }
    
    public static java.lang.Long zinterstore(java.lang.String arg0,java.lang.String[] arg1) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.zinterstore(arg0,arg1);
        } else {
            throw new JedisConnectionException("Cannot execute zinterstore with sharded instance.");
        }
    }
    
    public static java.lang.Long zinterstore(java.lang.String arg0,redis.clients.jedis.ZParams arg1,java.lang.String[] arg2) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.zinterstore(arg0,arg1,arg2);
        } else {
            throw new JedisConnectionException("Cannot execute zinterstore with sharded instance.");
        }
    }
    
    public static java.lang.Long zinterstore(byte[] arg0,redis.clients.jedis.ZParams arg1,byte[][] arg2) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.zinterstore(arg0,arg1,arg2);
        } else {
            throw new JedisConnectionException("Cannot execute zinterstore with sharded instance.");
        }
    }
    
    public static java.util.Set zrange(byte[] arg0,int arg1,int arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zrange(arg0,arg1,arg2);
    }
    
    public static java.util.Set zrange(java.lang.String arg0,int arg1,int arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zrange(arg0,arg1,arg2);
    }
    
    public static java.util.Set zrangeByScore(java.lang.String arg0,java.lang.String arg1,java.lang.String arg2) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.zrangeByScore(arg0,arg1,arg2);
        } else {
            throw new JedisConnectionException("Cannot execute zrangeByScore with sharded instance.");
        }
    }
    
    public static java.util.Set zrangeByScore(byte[] arg0,double arg1,double arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zrangeByScore(arg0,arg1,arg2);
    }
    
    public static java.util.Set zrangeByScore(java.lang.String arg0,double arg1,double arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zrangeByScore(arg0,arg1,arg2);
    }
    
    public static java.util.Set zrangeByScore(byte[] arg0,byte[] arg1,byte[] arg2) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.zrangeByScore(arg0,arg1,arg2);
        } else {
            throw new JedisConnectionException("Cannot execute zrangeByScore with sharded instance.");
        }
    }
    
    public static java.util.Set zrangeByScore(byte[] arg0,double arg1,double arg2,int arg3,int arg4) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zrangeByScore(arg0,arg1,arg2,arg3,arg4);
    }
    
    public static java.util.Set zrangeByScore(java.lang.String arg0,double arg1,double arg2,int arg3,int arg4) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zrangeByScore(arg0,arg1,arg2,arg3,arg4);
    }
    
    public static java.util.Set zrangeByScoreWithScores(byte[] arg0,double arg1,double arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zrangeByScoreWithScores(arg0,arg1,arg2);
    }
    
    public static java.util.Set zrangeByScoreWithScores(java.lang.String arg0,double arg1,double arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zrangeByScoreWithScores(arg0,arg1,arg2);
    }
    
    public static java.util.Set zrangeByScoreWithScores(java.lang.String arg0,double arg1,double arg2,int arg3,int arg4) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zrangeByScoreWithScores(arg0,arg1,arg2,arg3,arg4);
    }
    
    public static java.util.Set zrangeByScoreWithScores(byte[] arg0,double arg1,double arg2,int arg3,int arg4) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zrangeByScoreWithScores(arg0,arg1,arg2,arg3,arg4);
    }
    
    public static java.util.Set zrangeWithScores(java.lang.String arg0,int arg1,int arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zrangeWithScores(arg0,arg1,arg2);
    }
    
    public static java.util.Set zrangeWithScores(byte[] arg0,int arg1,int arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zrangeWithScores(arg0,arg1,arg2);
    }
    
    public static java.lang.Long zrank(byte[] arg0,byte[] arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zrank(arg0,arg1);
    }
    
    public static java.lang.Long zrank(java.lang.String arg0,java.lang.String arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zrank(arg0,arg1);
    }
    
    public static java.lang.Long zrem(java.lang.String arg0,java.lang.String arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zrem(arg0,arg1);
    }
    
    public static java.lang.Long zrem(byte[] arg0,byte[] arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zrem(arg0,arg1);
    }
    
    public static java.lang.Long zremrangeByRank(java.lang.String arg0,int arg1,int arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zremrangeByRank(arg0,arg1,arg2);
    }
    
    public static java.lang.Long zremrangeByRank(byte[] arg0,int arg1,int arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zremrangeByRank(arg0,arg1,arg2);
    }
    
    public static java.lang.Long zremrangeByScore(java.lang.String arg0,double arg1,double arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zremrangeByScore(arg0,arg1,arg2);
    }
    
    public static java.lang.Long zremrangeByScore(byte[] arg0,double arg1,double arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zremrangeByScore(arg0,arg1,arg2);
    }
    
    public static java.util.Set zrevrange(java.lang.String arg0,int arg1,int arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zrevrange(arg0,arg1,arg2);
    }
    
    public static java.util.Set zrevrange(byte[] arg0,int arg1,int arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zrevrange(arg0,arg1,arg2);
    }
    
    public static java.util.Set zrevrangeByScore(java.lang.String arg0,java.lang.String arg1,java.lang.String arg2) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.zrevrangeByScore(arg0,arg1,arg2);
        } else {
            throw new JedisConnectionException("Cannot execute zrevrangeByScore with sharded instance.");
        }
    }
    
    public static java.util.Set zrevrangeByScore(byte[] arg0,byte[] arg1,byte[] arg2) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.zrevrangeByScore(arg0,arg1,arg2);
        } else {
            throw new JedisConnectionException("Cannot execute zrevrangeByScore with sharded instance.");
        }
    }
    
    public static java.util.Set zrevrangeByScore(java.lang.String arg0,double arg1,double arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zrevrangeByScore(arg0,arg1,arg2);
    }
    
    public static java.util.Set zrevrangeByScore(byte[] arg0,double arg1,double arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zrevrangeByScore(arg0,arg1,arg2);
    }
    
    public static java.util.Set zrevrangeByScore(java.lang.String arg0,double arg1,double arg2,int arg3,int arg4) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zrevrangeByScore(arg0,arg1,arg2,arg3,arg4);
    }
    
    public static java.util.Set zrevrangeByScore(byte[] arg0,double arg1,double arg2,int arg3,int arg4) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zrevrangeByScore(arg0,arg1,arg2,arg3,arg4);
    }
    
    public static java.util.Set zrevrangeByScoreWithScores(java.lang.String arg0,double arg1,double arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zrevrangeByScoreWithScores(arg0,arg1,arg2);
    }
    
    public static java.util.Set zrevrangeByScoreWithScores(byte[] arg0,double arg1,double arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zrevrangeByScoreWithScores(arg0,arg1,arg2);
    }
    
    public static java.util.Set zrevrangeByScoreWithScores(java.lang.String arg0,double arg1,double arg2,int arg3,int arg4) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zrevrangeByScoreWithScores(arg0,arg1,arg2,arg3,arg4);
    }
    
    public static java.util.Set zrevrangeByScoreWithScores(byte[] arg0,double arg1,double arg2,int arg3,int arg4) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zrevrangeByScoreWithScores(arg0,arg1,arg2,arg3,arg4);
    }
    
    public static java.util.Set zrevrangeWithScores(java.lang.String arg0,int arg1,int arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zrevrangeWithScores(arg0,arg1,arg2);
    }
    
    public static java.util.Set zrevrangeWithScores(byte[] arg0,int arg1,int arg2) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zrevrangeWithScores(arg0,arg1,arg2);
    }
    
    public static java.lang.Long zrevrank(byte[] arg0,byte[] arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zrevrank(arg0,arg1);
    }
    
    public static java.lang.Long zrevrank(java.lang.String arg0,java.lang.String arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zrevrank(arg0,arg1);
    }
    
    public static java.lang.Double zscore(java.lang.String arg0,java.lang.String arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zscore(arg0,arg1);
    }
    
    public static java.lang.Double zscore(byte[] arg0,byte[] arg1) {
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard(arg0);
        return jedis.zscore(arg0,arg1);
    }
    
    public static java.lang.Long zunionstore(byte[] arg0,byte[][] arg1) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.zunionstore(arg0,arg1);
        } else {
            throw new JedisConnectionException("Cannot execute zunionstore with sharded instance.");
        }
    }
    
    public static java.lang.Long zunionstore(java.lang.String arg0,java.lang.String[] arg1) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.zunionstore(arg0,arg1);
        } else {
            throw new JedisConnectionException("Cannot execute zunionstore with sharded instance.");
        }
    }
    
    public static java.lang.Long zunionstore(java.lang.String arg0,redis.clients.jedis.ZParams arg1,java.lang.String[] arg2) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.zunionstore(arg0,arg1,arg2);
        } else {
            throw new JedisConnectionException("Cannot execute zunionstore with sharded instance.");
        }
    }
    
    public static java.lang.Long zunionstore(byte[] arg0,redis.clients.jedis.ZParams arg1,byte[][] arg2) {
        if (!RedisConnectionManager.isSharded()) {
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
            return jedis.zunionstore(arg0,arg1,arg2);
        } else {
            throw new JedisConnectionException("Cannot execute zunionstore with sharded instance.");
        }
    }
    
}