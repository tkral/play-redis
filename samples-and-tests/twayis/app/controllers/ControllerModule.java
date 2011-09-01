package controllers;

import com.google.inject.AbstractModule;
import services.Redis;
import services.RedisImpl;
import services.Twayis;
import services.TwayisImpl;


/**
 * Guice module that defines components binding.
 * @author luciano
 */
public class ControllerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Redis.class).to(RedisImpl.class);
        bind(Twayis.class).to(TwayisImpl.class);
    }



}
