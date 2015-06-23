package com.igorkurilenko.gwt.samples.oauth2client.client.application;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.igorkurilenko.gwt.samples.oauth2client.client.application.response.ResponseModule;


public class ApplicationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new ResponseModule());

        bindPresenter(com.igorkurilenko.gwt.samples.oauth2client.client.application.ApplicationPresenter.class, com.igorkurilenko.gwt.samples.oauth2client.client.application.ApplicationPresenter.MyView.class, com.igorkurilenko.gwt.samples.oauth2client.client.application.ApplicationView.class,
                ApplicationPresenter.MyProxy.class);
    }
}
