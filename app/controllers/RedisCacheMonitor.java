package controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import play.cache.Cache;
import play.modules.redis.RedisCacheImpl;
import play.modules.redis.RedisPlugin;
import play.mvc.Controller;

public class RedisCacheMonitor extends Controller {

	public static void cacheInstance() {
		if (RedisPlugin.isRedisCacheEnabled()) {
			Map<String, Object> cacheInstanceInfo = new HashMap<String, Object>(2);
			cacheInstanceInfo.put("host", RedisCacheImpl.getCacheConnection().getClient().getHost());
			cacheInstanceInfo.put("port", RedisCacheImpl.getCacheConnection().getClient().getPort());
			renderJSON(cacheInstanceInfo);
		}
	}
	
	public static void cacheClientInfo() {
		if (RedisPlugin.isRedisCacheEnabled()) {
			String clientInfo = RedisCacheImpl.getCacheConnection().info();
			
			// Poor man's JSON conversion... 
			// Surround keys/values with quotes
			clientInfo = clientInfo.replaceAll("([^\\s:]+)", "\"$1\"");
			
			 // Replace CRLF with comma
			clientInfo = clientInfo.replaceAll("([\\s]+)", ",");
			
			// Drop last comma
			if (clientInfo.endsWith(",")) {
				clientInfo = clientInfo.substring(0, clientInfo.length() - 1);
			}
			
			renderJSON("{" + clientInfo + "}");
		}
	}
	
	public static void cacheContents() {
		if (RedisPlugin.isRedisCacheEnabled()) {
			Set<String> keys = RedisCacheImpl.getCacheConnection().keys("*");
			
			Map<String, Object> cacheContents = new TreeMap<String, Object>(Cache.get(keys.toArray(new String[keys.size()])));
			renderJSON(cacheContents);
		}
		
	}
}