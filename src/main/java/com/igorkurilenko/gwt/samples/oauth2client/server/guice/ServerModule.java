package com.igorkurilenko.gwt.samples.oauth2client.server.guice;

import com.gwtplatform.dispatch.rpc.server.guice.HandlerModule;
import com.igorkurilenko.gwt.samples.oauth2client.server.api.ApiModule;


public class ServerModule extends HandlerModule {
    @Override
    protected void configureHandlers() {
        install(new ApiModule());
    }
}
