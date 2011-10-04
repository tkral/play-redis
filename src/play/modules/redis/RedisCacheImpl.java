package play.modules.redis;

import play.Play;
import play.cache.CacheImpl;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisDataException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Play cache implementation using Redis.
 * 
 * @author Tim Kral
 */
public class RedisCacheImpl implements CacheImpl {

	private static RedisCacheImpl uniqueInstance = new RedisCacheImpl();
	
	static JedisPool connectionPool;
    static ThreadLocal<Jedis> cacheConnection = new ThreadLocal<Jedis>();
	
    private RedisCacheImpl() {  }

    static RedisCacheImpl getInstance() {
        return uniqueInstance;
    }

    public static Jedis getCacheConnection() {
    	if (cacheConnection.get() != null) {
    		return cacheConnection.get();
    	}
    	
    	Jedis connection = connectionPool.getResource();
    	cacheConnection.set(connection);
    	return connection;
    }
    
    public static void closeCacheConnection() {
        if (cacheConnection.get() != null) {
        	connectionPool.returnResource(cacheConnection.get());
        	cacheConnection.remove();
        }
    }
    
	@Override
	public void add(String key, Object value, int expiration) {
		if (!getCacheConnection().exists(key)) {
			set(key, value, expiration);
		}		
	}

	@Override
	public boolean safeAdd(String key, Object value, int expiration) {
		try {
			add(key, value, expiration);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void set(String key, Object value, int expiration) {
		Jedis jedis = getCacheConnection();
		
	    // Serialize to a byte array
		byte[] bytes = toByteArray(value);
		
		jedis.set(key.getBytes(), bytes);
		jedis.expire(key, expiration);
	}

	private static byte[] toByteArray(Object o) {
		ObjectOutputStream out = null;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream() ;
			out = new ObjectOutputStream(bos) ;
			out.writeObject(o);

			return bos.toByteArray();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (out != null) out.close();
			} catch (IOException e2) {
				throw new RuntimeException(e2);
			}
		}
	}
	
	@Override
	public boolean safeSet(String key, Object value, int expiration) {
		try {
			set(key, value, expiration);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void replace(String key, Object value, int expiration) {
		if (getCacheConnection().exists(key)) {
			set(key, value, expiration);
		}
	}

	@Override
	public boolean safeReplace(String key, Object value, int expiration) {
		try {
			replace(key, value, expiration);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Object get(String key) {
		byte[] bytes = getCacheConnection().get(key.getBytes());
		if (bytes == null) return null;

		return fromByteArray(bytes);
	}

	private static Object fromByteArray(byte[] bytes) {
		
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new ByteArrayInputStream(bytes)) {
               @Override
                protected Class<?> resolveClass(ObjectStreamClass desc)
                        throws IOException, ClassNotFoundException {
                    return Class.forName(desc.getName(), false, Play.classloader);
                }
            };
			return in.readObject();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (in != null) in.close();
			} catch (IOException e2) {
				throw new RuntimeException(e2);
			}
		}
	}
	
	@Override
	public Map<String, Object> get(String[] keys) {
		Map<String, Object> result = new HashMap<String, Object>(keys.length);
        for (String key : keys) {
            result.put(key, get(key));
        }
        return result;
	}

	@Override
	public long incr(String key, int by) {
		Object cacheValue = get(key);
		long sum;
		if (cacheValue == null) {
			Long newCacheValueLong = Long.valueOf(0L + by);
			getCacheConnection().set(key.getBytes(), toByteArray(newCacheValueLong));
			sum = newCacheValueLong.longValue();
		} else if (cacheValue instanceof Integer) {
			Integer newCacheValueInteger = (Integer)cacheValue + by;
			getCacheConnection().set(key.getBytes(), toByteArray(newCacheValueInteger));
			sum = newCacheValueInteger.longValue();
		} else if (cacheValue instanceof Long) {
			Long newCacheValueLong = (Long)cacheValue + by;
			getCacheConnection().set(key.getBytes(), toByteArray(newCacheValueLong));
			sum = newCacheValueLong.longValue();
		} else {
			throw new JedisDataException("Cannot incr on non-integer value (key: " + key + ")");
		}
		
		return sum;
	}

	@Override
	public long decr(String key, int by) {
		Object cacheValue = get(key);
		long difference;
		if (cacheValue == null) {
			Long newCacheValueLong = Long.valueOf(0L - by);
			getCacheConnection().set(key.getBytes(), toByteArray(newCacheValueLong));
			difference = newCacheValueLong.longValue();
		} else if (cacheValue instanceof Integer) {
			Integer newCacheValueInteger = (Integer)cacheValue - by;
			getCacheConnection().set(key.getBytes(), toByteArray(newCacheValueInteger));
			difference = newCacheValueInteger.longValue();
		} else if (cacheValue instanceof Long) {
			Long newCacheValueLong = (Long)cacheValue - by;
			getCacheConnection().set(key.getBytes(), toByteArray(newCacheValueLong));
			difference = newCacheValueLong.longValue();
		} else {
			throw new JedisDataException("Cannot decr on non-integer value (key: " + key + ")");
		}
		
		return difference;
	}

	@Override
	public void clear() {
		getCacheConnection().flushDB();
		// TODO: check return status code
	}

	@Override
	public void delete(String key) {
		getCacheConnection().del(key);
		// TODO: check return status code
	}

	@Override
	public boolean safeDelete(String key) {
		try {
			delete(key);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void stop() {
		getCacheConnection().flushAll();
		connectionPool.destroy();
	}

}
