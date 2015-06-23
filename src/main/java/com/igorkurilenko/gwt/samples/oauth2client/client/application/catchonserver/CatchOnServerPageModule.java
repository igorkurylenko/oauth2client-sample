package com.igorkurilenko.gwt.samples.oauth2client.client.application.catchonserver;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class CatchOnServerPageModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(CatchOnServerPagePresenter.class, CatchOnServerPagePresenter.MyView.class,
                CatchOnServerPageView.class, CatchOnServerPagePresenter.MyProxy.class);
    }
}
