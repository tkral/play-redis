/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import javax.inject.Inject;

import play.mvc.With;
import redis.clients.jedis.Jedis;
import services.Redis;
import services.RedisImpl;


/**
 *
 * @author luciano
 */
@With(RedisImpl.class)
public class Security extends Secure.Security {
    
    @Inject
    static Redis redis;

    static boolean authentify(String username, String password) {
        
        Jedis jedis = redis.connect();
        String userid = jedis.get("username:" + username + ":id");
        if (userid!=null) {
        	String pw = jedis.get("uid:"+userid+":password");
        	if (pw.equals(password)) return true;
        }
        
        return false;
    }

}
