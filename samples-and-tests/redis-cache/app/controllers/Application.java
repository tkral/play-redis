package controllers;

import play.cache.Cache;
import play.libs.Time;
import play.mvc.Controller;

public class Application extends Controller {

    public static void index() {
    	render();
    }

    public static void set(String key, String value, String expiration) {
    	
    	try {
    		if (expiration == null || "".equals(expiration)) {
    			Cache.set(key, value);
    		} else {
    			Cache.set(key, value, expiration);
    		}
    	} catch (IllegalArgumentException e) {
    		flash.error("Illegal expiration value %s (e.g. 10s, 1mn, 3h)", expiration);
    	}
    	
    	index();
    }
    
    public static void replace(String key, String value, String expiration) {
    	
    	try {
    		if (expiration == null || "".equals(expiration)) {
    			Cache.replace(key, value);
    		} else {
    			Cache.replace(key, value, expiration);
    		}
    	} catch (IllegalArgumentException e) {
    		flash.error("Illegal expiration value %s (e.g. 10s, 1mn, 3h)", expiration);
    	}
    	
    	index();
    }
}