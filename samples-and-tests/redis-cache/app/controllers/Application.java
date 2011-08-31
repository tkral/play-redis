package controllers;

import play.cache.Cache;
import play.mvc.Controller;

public class Application extends Controller {

    public static void index() {
    	render();
    }

//    public static void add(String key, String value) {
//    	Cache.add(key, value);
//    	index();
//    }
//    
    public static void set(String key, String value) {
    	Cache.set(key, value);
    	index();
    }
//    
//    public static void replace(String key, String value) {
//    	Cache.replace(key, value);
//    	index();
//    }
}