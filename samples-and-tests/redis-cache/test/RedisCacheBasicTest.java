import models.TestModelObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import play.cache.Cache;
import play.modules.redis.RedisCacheImpl;
import play.test.UnitTest;

/**
 * Tests basic Play cache functionality using the Redis implementation.
 * 
 * @author Tim Kral
 */
public class RedisCacheBasicTest extends UnitTest {

	@Before
	public void checkCacheImpl() {
		Assert.assertEquals("Wrong cache implementation", RedisCacheImpl.class, Cache.cacheImpl.getClass());
	}
	
	@After
	public void clearCache() {
		Cache.clear();
	}
	
    @Test
    public void testBasicCache() {
		Cache.add("testBasicCache", "value");
		Object cacheValue = Cache.get("testBasicCache");
		Assert.assertTrue("Unexpected Object class: " + cacheValue.getClass(), cacheValue instanceof String);
		Assert.assertEquals("Unexpected value retrieved from cache.",
				"value", (String)cacheValue);
    }

    @Test
    public void testBasicCacheInt() {
    	Cache.add("testBasicCacheInt", 1);
    	int cacheValue = Cache.get("testBasicCacheInt", Integer.class);
    	Assert.assertEquals("Unexpected value retrieved from cache.", 1, cacheValue);
    }
    
    @Test
    public void testBasicCacheDouble() {
    	Cache.add("testBasicCacheDouble", 2.028486d);
    	double cacheValue = Cache.get("testBasicCacheDouble", Double.class);
    	Assert.assertEquals("Unexpected value retrieved from cache.", 2.028486d, cacheValue, 0d);
    }

    @Test
    public void testBasicCacheModelObject() {
        Cache.add("testModelObject", new TestModelObject(1L, "TestProperty"));
        TestModelObject obj = Cache.get("testModelObject", TestModelObject.class);
        assertNotNull(obj);
        assertEquals((Long)1L, obj.id);
        assertEquals("TestProperty", obj.testProperty);
    }
    
    @Test
    public void testCacheDecr() {
    	Cache.add("testCacheDecr", 10);
    	long decrValue = Cache.decr("testCacheDecr");
    	Assert.assertEquals("Unexpected return from cache decr.", 9L, decrValue);
    	
    	int cacheValue = (Integer)Cache.get("testCacheDecr");
    	Assert.assertEquals("Unexpected value retrieved after cache decr.", 9, cacheValue);
    }
    
    @Test
    public void testCacheDelete() {
    	Cache.add("testCacheDelete", "value");
    	Cache.delete("testCacheDelete");
    	Assert.assertNull("Expected cache key to be deleted.", Cache.get("testCacheDelete"));
    }
    
    @Test
    public void testCacheExpiration() {
    	Cache.add("testCacheExpiration", "value", "1s");
    	pause(2000);
    	Assert.assertNull("Expected cache key to have expired.", Cache.get("testCacheExpiration"));
    }
    
    @Test
    public void testCacheIncr() {
    	Cache.add("testCacheIncr", 5);
    	long incrValue = Cache.incr("testCacheIncr");
    	Assert.assertEquals("Unexpected return from cache incr.", 6L, incrValue);
    	
    	int cacheValue = (Integer)Cache.get("testCacheIncr");
    	Assert.assertEquals("Unexpected value retrieved after cache incr.", 6, cacheValue);
    }
    
    @Test
    public void testCacheReplace() {
    	Cache.add("testCacheReplace", "value1");
    	Cache.replace("testCacheReplace", "value2");
    	
    	String cacheValue = (String)Cache.get("testCacheReplace");
    	Assert.assertEquals("Unexpected value after cache replace.", "value2", cacheValue);
    }
}
