package com.igorkurilenko.gwt.samples.oauth2client.client.application;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;


public class ApplicationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class,
                ApplicationView.class, ApplicationPresenter.MyProxy.class);
    }
}
