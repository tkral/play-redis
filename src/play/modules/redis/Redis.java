package play.modules.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 
 * @author Tim Kral
 */
public class Redis {

	public static JedisPool connectionPool;
    static ThreadLocal<Jedis> redisConnection = new ThreadLocal<Jedis>();

    /**
     * Retrieves a Redis connection from the connection pool.
     * @return A valid Redis connection
     */
    public static Jedis getConnection() {
    	if (redisConnection.get() != null) {
    		return redisConnection.get();
    	}
    	
    	Jedis connection = connectionPool.getResource();
    	redisConnection.set(connection);
    	return connection;
    }
    
    /**
     * Close the connection opened for the current thread.
     */
    public static void closeConnection() {
        if (redisConnection.get() != null) {
        	connectionPool.returnResource(redisConnection.get());
        	redisConnection.remove();
        }
    }    
    /**
     * Destroy the connection pool.
     */
    public static void destroy() {
    	connectionPool.destroy();
    }
    
}
