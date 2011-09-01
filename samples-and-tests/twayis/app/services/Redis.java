/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package services;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
/**
 *
 * @author luciano
 */
//TODO: Play! module
public interface Redis {
    public Jedis connect();
    public void disconnect(Jedis jedis);
}
