package com.igorkurilenko.gwt.samples.oauth2client.client.application.response;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;


public class ResponseModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(ResponsePresenter.class, ResponsePresenter.MyView.class, ResponseView.class,
                ResponsePresenter.MyProxy.class);
    }
}
