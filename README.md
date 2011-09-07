# Redis Plugin for Play Framework
This plugin allow you to integrate redis with your Play application.  It consists of three parts:

1. A redis client used to persist data to a redis instance.
2. A redis implementation of Play's cache interface.  This allows you to use redis for your caching needs.
3. A redis cache monitor that helps you debug issues with your redis cache.

With this plugin, you can choose to use redis as a persistent data store or a caching system or both. **Note** that if you choose both, you'll need separate redis instances.  The cache is flushed at application shutdown.

## Using the Redis Client
Configuring the redis client is easy.  Simply add this property to your `conf/application.conf` file:

```
redis.url=redis://username:password@host:port
```

Within your application code, the redis client is available via the `play.modules.redis.Redis` class.  For example, you can set and get values with the following code:

```java
Redis.set("key", "value");
Redis.get("key");
```

Note that this class abstracts away the client implementation.  If for some reason, you wish to get the native client, that is possible as well:

```java
RedisConnectionManager.getRawConnection().set("key", "value");
```

`RedisConnectionManager.getRawConnection` will return a `Jedis` client.

### Using the Redis Client with Shared Instances
The plugin supports sharded instances.  Within your `conf/application.conf` file, specify the connection urls to the various shards:

```
redis.1.url=redis://username:password@host:port
redis.2.url=redis://...
```

Now you may use the Redis client the same way as above.  If you wish to get a native client, you can with the following:

```java
RedisConnectionManager.getRawConnectionFromShard("key");
```

This will return a `Jedis` client to the shard containing the key argument;

## Using Redis for Caching
Again, the configuration is easy.  Add these properties to your `conf/application.conf` file:

```
redis.cache=enabled
redis.cache.url=redis://username:password@host:port
```

Now you can use the Cache interface like you normally would in your Play application:

```java
Cache.set("key", "value", "30s");
```

## Using the Redis Cache Monitor
This monitor helps debug your cache while your Play app is running.  Simply add this to your `routes` file:

```
*   /cache       module:redis
```

Now you can drop the cacheMonitor tag in any of your views:

```
#{cacheMonitor /}
```

The cache monitor will report your cache contents as well as information about your cache redis instance.
