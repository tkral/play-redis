package controllers;

import play.cache.Cache;
import play.libs.Time;
import play.mvc.Controller;

public class Application extends Controller {

    public static void index() {
    	render();
    }

    public static void clear() {
    	Cache.clear();
    	index();
    }
    
    public static void set(String key, String value) {
    	Cache.set(key, value);
    	index();
    }
    
    public static void setWithExpire(String key, String value, String expiration) {
    	try {
    		Cache.set(key, value, expiration);
    	} catch (IllegalArgumentException e) {
    		flash.error("Illegal expiration value %s (e.g. 10s, 1mn, 3h)", expiration);
    	}
    	
    	index();
    }

    public static void replace(String key, String value) {
    	Cache.replace(key, value);
    	index();
    }
    
    public static void replaceWithExpire(String key, String value, String expiration) {
    	try {
    		Cache.replace(key, value, expiration);
    	} catch (IllegalArgumentException e) {
    		flash.error("Illegal expiration value %s (e.g. 10s, 1mn, 3h)", expiration);
    	}
    	
    	index();
    }
}