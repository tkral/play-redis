# Redis Plugin for Play Framework
This plugin allow you to integrate redis with your Play application.  It consists of three parts:

1. A redis connection manager used to persist data to a redis instance.
2. A redis implementation of Play's cache interface.  This allows you to use redis for your caching needs.
3. A redis cache monitor that helps you debug issues with your redis cache.

With this plugin, you can choose to use redis as a persistent data store or a caching system or both. **Note** that if you choose both, you'll need separate redis instances.  The cache is flushed at application shutdown.

## Using the Redis Connection Manager
Configuring the redis connection manager is easy.  Simply add this property to your `conf/application.conf` file:

```
    redis.url=redis://username:password@host:port
```

Within your application code, you can get a redis connection with:

```java
Redis.getConnection();
```

Now you can make direct calls to your redis instance.  For example:

```java
Redis.getConnection().set("key", "value");
```

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
