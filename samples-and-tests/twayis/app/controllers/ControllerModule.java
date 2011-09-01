package controllers;

import services.Twayis;
import services.TwayisImpl;

import com.google.inject.AbstractModule;


/**
 * Guice module that defines components binding.
 * @author luciano
 */
public class ControllerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Twayis.class).to(TwayisImpl.class);
    }



}
