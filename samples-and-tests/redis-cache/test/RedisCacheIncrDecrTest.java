import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import play.cache.Cache;
import play.modules.redis.RedisCacheImpl;
import play.test.UnitTest;
import redis.clients.jedis.exceptions.JedisDataException;

/**
 * Tests cache incr and decr functions using the Redis implementation.
 * 
 * @author Tim Kral
 */
public class RedisCacheIncrDecrTest extends UnitTest {

	@Before
	public void checkCacheImpl() {
		Assert.assertEquals("Wrong cache implementation", RedisCacheImpl.class, Cache.cacheImpl.getClass());
	}
	
	@After
	public void clearCache() {
		Cache.clear();
	}
	
	@Test
	public void testDecrEmptyKey() {
		long decrValue = Cache.decr("testDecrEmptyKey");
		Assert.assertEquals("Unexpected return from cache decr.", -1L, decrValue);
		
		long cacheValue = Cache.get("testDecrEmptyKey", Long.class);
		Assert.assertEquals("Unexpected value retrieved after cache decr.", -1L, cacheValue);
	}
	
	@Test
	public void testDecrInt() {
		Cache.add("testDecrInt", 10);
		long decrValue = Cache.decr("testDecrInt");
		Assert.assertEquals("Unexpected return from cache decr.", 9L, decrValue);
		
    	int cacheValue = Cache.get("testDecrInt", Integer.class);
    	Assert.assertEquals("Unexpected value retrieved after cache decr.", 9, cacheValue);
	}

	@Test
	public void testDecrIntBy() {
		Cache.add("testDecrIntBy", 10);
		long decrValue = Cache.decr("testDecrIntBy", 2);
		Assert.assertEquals("Unexpected return from cache decr.", 8L, decrValue);
		
    	int cacheValue = Cache.get("testDecrIntBy", Integer.class);
    	Assert.assertEquals("Unexpected value retrieved after cache decr.", 8, cacheValue);
	}
	
    @Test
    public void testDecrLong() {
    	Cache.add("testDecrLong", 10L);
    	long decrValue = Cache.decr("testDecrLong");
    	Assert.assertEquals("Unexpected return from cache decr.", 9L, decrValue);
    	
    	long cacheValue = Cache.get("testDecrLong", Long.class);
    	Assert.assertEquals("Unexpected value retrieved after cache decr.", 9L, cacheValue);
    }
    
    @Test
    public void testDecrLongBy() {
    	Cache.add("testDecrLongBy", 10L);
    	long decrValue = Cache.decr("testDecrLongBy", 2);
    	Assert.assertEquals("Unexpected return from cache decr.", 8L, decrValue);
    	
    	long cacheValue = Cache.get("testDecrLongBy", Long.class);
    	Assert.assertEquals("Unexpected value retrieved after cache decr.", 8L, cacheValue);
    }
    
    @Test
    public void testDecrNonInt() {
    	Cache.add("testDecrNonInt", "value");
    	try {
    		Cache.decr("testDecrNonInt");
    		Assert.fail("Cache decr should not be allowed with non-integer value");
    	} catch (Exception e) {
    		assertEquals("Unexpected exception thrown.", JedisDataException.class, e.getClass());
    	}
    }
    
	@Test
	public void testIncrEmptyKey() {
		long incrValue = Cache.incr("testIncrEmptyKey");
		Assert.assertEquals("Unexpected return from cache incr.", 1L, incrValue);
		
		long cacheValue = Cache.get("testIncrEmptyKey", Long.class);
		Assert.assertEquals("Unexpected value retrieved after cache incr.", 1L, cacheValue);
	}
	
	@Test
	public void testIncrInt() {
		Cache.add("testIncrInt", 10);
		long incrValue = Cache.incr("testIncrInt");
		Assert.assertEquals("Unexpected return from cache incr.", 11L, incrValue);
		
    	int cacheValue = Cache.get("testIncrInt", Integer.class);
    	Assert.assertEquals("Unexpected value retrieved after cache incr.", 11, cacheValue);
	}

	@Test
	public void testIncrIntBy() {
		Cache.add("testIncrIntBy", 10);
		long incrValue = Cache.incr("testIncrIntBy", 2);
		Assert.assertEquals("Unexpected return from cache incr.", 12L, incrValue);
		
    	int cacheValue = (Integer)Cache.get("testIncrIntBy");
    	Assert.assertEquals("Unexpected value retrieved after cache incr.", 12, cacheValue);
	}
	
    @Test
    public void testIncrLong() {
    	Cache.add("testIncrLong", 10L);
    	long incrValue = Cache.incr("testIncrLong");
    	Assert.assertEquals("Unexpected return from cache incr.", 11L, incrValue);
    	
    	long cacheValue = Cache.get("testIncrLong", Long.class);
    	Assert.assertEquals("Unexpected value retrieved after cache incr.", 11L, cacheValue);
    }
    
    @Test
    public void testIncrLongBy() {
    	Cache.add("testIncrLongBy", 10L);
    	long incrValue = Cache.incr("testIncrLongBy", 2);
    	Assert.assertEquals("Unexpected return from cache incr.", 12L, incrValue);
    	
    	long cacheValue = Cache.get("testIncrLongBy", Long.class);
    	Assert.assertEquals("Unexpected value retrieved after cache incr.", 12L, cacheValue);
    }
    
    @Test
    public void testIncrNonInt() {
    	Cache.add("testIncrNonInt", "value");
    	try {
    		Cache.incr("testIncrNonInt");
    		Assert.fail("Cache incr should not be allowed with non-integer value");
    	} catch (Exception e) {
    		assertEquals("Unexpected exception thrown.", JedisDataException.class, e.getClass());
    	}
    }
}
