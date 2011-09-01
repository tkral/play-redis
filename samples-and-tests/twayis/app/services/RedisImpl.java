/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.pool.impl.GenericObjectPool.Config;

import play.Logger;
import play.Play;
import play.exceptions.ConfigurationException;
import play.mvc.Controller;
import play.mvc.Finally;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Protocol;

/**
 *
 * @author luciano
 */
// TODO: Play! module
public class RedisImpl extends Controller implements Redis {

	private static final ThreadLocal<Jedis> CURRENT_JEDIS = new ThreadLocal<Jedis>();
	
	static JedisPool jedisPool;

    public Jedis connect() {
    	// We'll maintain the same connection
    	// throughout the request
    	if (CURRENT_JEDIS.get() != null) {
    		return CURRENT_JEDIS.get();
    	}
    	
    	Jedis jedis = getPool().getResource();
    	CURRENT_JEDIS.set(jedis);
        return jedis;
    }
    
    public void disconnect(Jedis jedis) {
    	if (jedisPool != null) {
    		jedisPool.returnResource(jedis);
    	}
    }
    
    JedisPool getPool() {
    	if (jedisPool == null) {
    		jedisPool = initPool();
    		
    		// Make sure the pool is destroyed during shutdown
    		// (as per https://github.com/xetorthio/jedis/wiki)
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    jedisPool.destroy();
                }
            });
    	}
    	
    	return jedisPool;
    }
    
    JedisPool initPool() {
    	
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
    		throw new ConfigurationException("Bad configuration for redis: missing redis.url");
    	}
    	
    	int port = redisUri.getPort();
    	if (port < 0) {
    	    port = Protocol.DEFAULT_PORT;
    	}
    	
    	int timeout;
    	if (Play.configuration.containsKey("redis.timeout")) {
    		timeout = Integer.parseInt(Play.configuration.getProperty("redis.timeout"));
    	} else {
    		timeout = Protocol.DEFAULT_TIMEOUT;
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
    
    // This is run at the end of each request to ensure that
    // the connection is returned to the pool.
    // (as per https://github.com/xetorthio/jedis/wiki)
	@Finally
	static void disconnectRemainingConnection() {
    	if (CURRENT_JEDIS.get() != null) {
    		
    		if (jedisPool != null) {
    			jedisPool.returnResource(CURRENT_JEDIS.get());
    		}
    		
    		CURRENT_JEDIS.remove();
    	}
	}
}
