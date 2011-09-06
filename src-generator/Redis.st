package play.modules.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * Redis connector for the Play framework.
 *
 * **DO NOT EDIT**.  This class was auto-generated.
 * See play.modules.redis.generator.RedisGenerator.
 *
 * @author Tim Kral
 **/
public class Redis {

    $redisMethods:{m | 
    public static $m; format="returnType"$ $m; format="name"$($m; format="paramListWithTypes"$) \{
    $if(m.isAvailableForShards)$
        Jedis jedis = RedisConnectionManager.getRawConnectionFromShard($m; format="firstParamName"$);
        return jedis.$m; format="name"$($m; format="paramList"$);
    $else$
        if (!RedisConnectionManager.isSharded()) \{
            Jedis jedis = RedisConnectionManager.getRawConnectionInternal();
        $if(m.isVoidReturnType)$
            jedis.$m; format="name"$($m; format="paramList"$);
        $else$
            return jedis.$m; format="name"$($m; format="paramList"$);
        $endif$
        \} else \{
            throw new JedisConnectionException("Cannot execute $m; format="name"$ with sharded instance.");
        \}
    $endif$
    \}
    }$
}