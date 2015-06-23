package com.igorkurilenko.gwt.samples.oauth2client.server.api;

import com.google.inject.AbstractModule;
import com.igorkurilenko.gwt.samples.oauth2client.shared.api.TokenResource;

public class ApiModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(TokenResource.class).to(TokenResourceImpl.class);
    }
}
