package com.igorkurilenko.gwt.samples.oauth2client.client.application.storeonclient;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;


public class StoreOnClientPageModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(StoreOnClientPagePresenter.class, StoreOnClientPagePresenter.MyView.class,
                StoreOnClientPageView.class, StoreOnClientPagePresenter.MyProxy.class);
    }
}
