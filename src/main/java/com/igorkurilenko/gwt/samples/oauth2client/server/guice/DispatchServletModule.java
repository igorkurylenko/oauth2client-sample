package com.igorkurilenko.gwt.samples.oauth2client.server.guice;

import com.arcbees.guicyresteasy.GuiceRestEasyFilterDispatcher;
import com.google.inject.servlet.ServletModule;

import com.igorkurilenko.gwt.samples.oauth2client.shared.api.ApiPaths;

public class DispatchServletModule extends ServletModule {
    @Override
    public void configureServlets() {
        filter(ApiPaths.ROOT + "/*").through(GuiceRestEasyFilterDispatcher.class);
    }
}
