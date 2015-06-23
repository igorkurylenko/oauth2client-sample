package com.igorkurilenko.gwt.samples.oauth2client.server.guice;

import com.gwtplatform.dispatch.rpc.server.guice.HandlerModule;
import com.igorkurilenko.gwt.samples.oauth2client.server.dispatch.SendTextToServerHandler;
import com.igorkurilenko.gwt.samples.oauth2client.shared.dispatch.SendTextToServerAction;


public class ServerModule extends HandlerModule {
    @Override
    protected void configureHandlers() {
        bindHandler(SendTextToServerAction.class, SendTextToServerHandler.class);
    }
}
