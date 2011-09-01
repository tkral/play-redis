/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import play.modules.redis.Redis;


/**
 *
 * @author luciano
 */
public class Security extends Secure.Security {
    
    static boolean authentify(String username, String password) {
        
        String userid = Redis.getConnection().get("username:" + username + ":id");
        if (userid!=null) {
        	String pw = Redis.getConnection().get("uid:"+userid+":password");
        	if (pw.equals(password)) return true;
        }
        
        return false;
    }

}
