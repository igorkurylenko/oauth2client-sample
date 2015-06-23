package com.igorkurilenko.gwt.samples.oauth2client.client.gin;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.gwtplatform.mvp.shared.proxy.RouteTokenFormatter;
import com.igorkurilenko.gwt.samples.oauth2client.client.application.ApplicationModule;
import com.igorkurilenko.gwt.samples.oauth2client.client.place.NameTokens;
import com.igorkurilenko.gwt.samples.oauth2client.client.resources.ResourceLoader;

public class ClientModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new DefaultModule.Builder()
                .tokenFormatter(RouteTokenFormatter.class)
                .defaultPlace(NameTokens.STORE_ON_CLIENT)
                .errorPlace(NameTokens.STORE_ON_CLIENT)
                .unauthorizedPlace(NameTokens.STORE_ON_CLIENT)
                .build());
        install(new ApplicationModule());

        bind(ResourceLoader.class).asEagerSingleton();
    }
}
