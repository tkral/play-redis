package play.modules.redis;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import play.Logger;
import play.Play;
import play.PlayPlugin;
import play.cache.Cache;
import play.exceptions.ConfigurationException;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;


/**
 * Play plugin for Redis.
 * 
 * @author Tim Kral
 */
public class RedisPlugin extends PlayPlugin {

	private boolean createdRedisCache;
	private boolean createdRedis;
	
	public static boolean isRedisCacheEnabled() {
		return Play.configuration.getProperty("redis.cache", "disabled").equals("enabled");
	}
	
	@Override
	public void onConfigurationRead() {
		if (isRedisCacheEnabled()) {
	    	if (Play.configuration.containsKey("redis.cache.url")) {
	    	    String redisCacheUrl = Play.configuration.getProperty("redis.cache.url");
	    	    Logger.info("Connecting to redis cache with %s", redisCacheUrl);
	    	    RedisConnectionInfo redisConnInfo = new RedisConnectionInfo(redisCacheUrl, Play.configuration.getProperty("redis.cache.timeout"));
	    	    
	    	    RedisCacheImpl.connectionPool = redisConnInfo.getConnectionPool();
	    	    Cache.forcedCacheImpl = RedisCacheImpl.getInstance();
	    	    createdRedisCache = true;
	    	} else {
	    		throw new ConfigurationException("Bad configuration for redis cache: missing redis.cache.url");
	    	}
		}
	}
	
	@Override
	public void onApplicationStart() {
    	if (Play.configuration.containsKey("redis.url")) {
    	    String redisUrl = Play.configuration.getProperty("redis.url");
    	    Logger.info("Connecting to redis with %s", redisUrl);
    	    RedisConnectionInfo redisConnInfo = new RedisConnectionInfo(redisUrl, Play.configuration.getProperty("redis.timeout"));
    	    
        	RedisConnectionManager.connectionPool = redisConnInfo.getConnectionPool();
        	createdRedis = true;
    	} else if(Play.configuration.containsKey("redis.1.url")) {
    		int nb = 1;
    		
    		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
            while (Play.configuration.containsKey("redis." + nb + ".url")) {
            	RedisConnectionInfo redisConnInfo = new RedisConnectionInfo(Play.configuration.getProperty("redis." + nb + ".url"), Play.configuration.getProperty("redis.timeout"));
            	shards.add(redisConnInfo.getShardInfo());
                nb++;
            }
            
            RedisConnectionManager.shardedConnectionPool = new ShardedJedisPool(new JedisPoolConfig(), shards, ShardedJedis.DEFAULT_KEY_TAG_PATTERN);
            createdRedis = true;
    	} else {
    		if (!createdRedisCache) Logger.warn("No redis.url found in configuration. Redis will not be available.");
    	}
    	
	}
	
	@Override
	public void onApplicationStop() {
		// Redis cache is destroyed in Cache.stop (see Play.stop)
		if (createdRedis) RedisConnectionManager.destroy();
	}
	
    @Override
    public void invocationFinally() {
    	if (createdRedisCache) RedisCacheImpl.closeCacheConnection();
    	if (createdRedis) RedisConnectionManager.closeConnection();
    }
    
    private static class RedisConnectionInfo {
    	private final String host;
    	private final int port;
    	private final String password;
    	private final int timeout;
    	
    	RedisConnectionInfo(String redisUrl, String timeoutStr) {
    	    URI redisUri;
    		try {
    	        redisUri = new URI(redisUrl);
    	    } catch (URISyntaxException e) {
    	        throw new ConfigurationException("Bad configuration for redis: unable to parse redis url (" + redisUrl + ")");
    	    }
    		
    	    host = redisUri.getHost();
    	    
        	if (redisUri.getPort() > 0) {
        		port = redisUri.getPort();
        	} else {
        	    port = Protocol.DEFAULT_PORT;
        	}
        	
        	String userInfo = redisUri.getUserInfo();
        	if (userInfo != null) {
        	    String[] parsedUserInfo = userInfo.split(":");
        	    password = parsedUserInfo[parsedUserInfo.length - 1];
        	} else {
        		password = null;
        	}
        	
        	if (timeoutStr == null) {
        		timeout = Protocol.DEFAULT_TIMEOUT;
        	} else {
        		timeout = Integer.parseInt(timeoutStr);
        	}
    	}
    	
    	JedisPool getConnectionPool() {
    		if (password == null) {
    			return new JedisPool(new JedisPoolConfig(), host, port, timeout);
    		}
    		
    		return new JedisPool(new JedisPoolConfig(), host, port, timeout, password);
    	}
    	
    	JedisShardInfo getShardInfo() {
    		JedisShardInfo si = new JedisShardInfo(host, port, timeout);
    		si.setPassword(password);
    		return si;
    	}
    }
}
