package com.igorkurilenko.gwt.samples.oauth2client.client.application;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.igorkurilenko.gwt.samples.oauth2client.client.application.catchonserver.CatchOnServerPageModule;
import com.igorkurilenko.gwt.samples.oauth2client.client.application.storeonclient.StoreOnClientPageModule;

public class ApplicationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new StoreOnClientPageModule());
        install(new CatchOnServerPageModule());

        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class,
                ApplicationView.class, ApplicationPresenter.MyProxy.class);
    }
}
