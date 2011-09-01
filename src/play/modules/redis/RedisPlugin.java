package play.modules.redis;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.pool.impl.GenericObjectPool.Config;

import play.Logger;
import play.Play;
import play.PlayPlugin;
import play.cache.Cache;
import play.exceptions.ConfigurationException;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Protocol;


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
		URI redisCacheUri;
		if (isRedisCacheEnabled()) {
	    	if (Play.configuration.containsKey("redis.cache.url")) {
	    	    String redisCacheUrl = Play.configuration.getProperty("redis.cache.url");
	    	    Logger.info("Connecting to redis cache with %s", redisCacheUrl);
	    	    try {
	    	        redisCacheUri = new URI(redisCacheUrl);
	    	    } catch (URISyntaxException e) {
	    	        throw new ConfigurationException("Bad configuration for redis cache: unable to parse redis.cache.url");
	    	    }
	    	    
	    	    RedisCacheImpl.connectionPool = configureConnectionPool(redisCacheUri, parseTimeout("redis.cache.timeout"));
	    	    Cache.forcedCacheImpl = RedisCacheImpl.getInstance();
	    	    createdRedisCache = true;
	    	} else {
	    		throw new ConfigurationException("Bad configuration for redis cache: missing redis.cache.url");
	    	}
		}
	}
	
	@Override
	public void onApplicationStart() {
    	URI redisUri;
    	if (Play.configuration.containsKey("redis.url")) {
    	    String redisUrl = Play.configuration.getProperty("redis.url");
    	    Logger.info("Connecting to redis with %s", redisUrl);
    	    try {
    	        redisUri = new URI(redisUrl);
    	    } catch (URISyntaxException e) {
    	        throw new ConfigurationException("Bad configuration for redis: unable to parse redis.url");
    	    }
    	} else {
    		if (!createdRedisCache) Logger.warn("No redis.url found in configuration. Redis will not be available.");
    		return;
    	}
    	
    	Redis.connectionPool = configureConnectionPool(redisUri, parseTimeout("redis.timeout"));
    	createdRedis = true;
	}
	
	private int parseTimeout(String timeoutProperty) {
    	if (Play.configuration.containsKey(timeoutProperty)) {
    		return Integer.parseInt(Play.configuration.getProperty(timeoutProperty));
    	} 
    	
    	return Protocol.DEFAULT_TIMEOUT;
	}
	
	private JedisPool configureConnectionPool(URI redisUri, int timeout) {
    	int port = redisUri.getPort();
    	if (port < 0) {
    	    port = Protocol.DEFAULT_PORT;
    	}
    	
    	String password;
    	String userInfo = redisUri.getUserInfo();
    	if (userInfo != null) {
    	    String[] parsedUserInfo = userInfo.split(":");
    	    password = parsedUserInfo[parsedUserInfo.length - 1];
    	} else {
    	    throw new ConfigurationException("Bad configuration for redis: missing password");
    	}
    	
    	// Ensure we get a healthy connection each time
    	Config config = new Config();
    	config.testOnBorrow = true;
    	return new JedisPool(config, redisUri.getHost(), port, timeout, password);
	}
	
	@Override
	public void onApplicationStop() {
		// Redis cache is destroyed in Cache.stop (see Play.stop)
		if (createdRedis) Redis.destroy();
	}
	
    @Override
    public void invocationFinally() {
    	if (createdRedisCache) RedisCacheImpl.closeCacheConnection();
    	if (createdRedis) Redis.closeConnection();
    }
}
