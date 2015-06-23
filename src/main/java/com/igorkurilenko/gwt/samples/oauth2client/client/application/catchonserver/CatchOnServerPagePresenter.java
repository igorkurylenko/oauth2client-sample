package com.igorkurilenko.gwt.samples.oauth2client.client.application.catchonserver;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.igorkurilenko.gwt.samples.oauth2client.client.application.ApplicationPresenter;
import com.igorkurilenko.gwt.samples.oauth2client.client.place.NameTokens;


public class CatchOnServerPagePresenter
        extends Presenter<CatchOnServerPagePresenter.MyView, CatchOnServerPagePresenter.MyProxy> {
    interface MyView extends View {
    }

    @ProxyStandard
    @NameToken(NameTokens.CATCH_ON_SERVER)
    @NoGatekeeper
    interface MyProxy extends ProxyPlace<CatchOnServerPagePresenter> {
    }

    @Inject
    CatchOnServerPagePresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN_CONTENT);
    }
}
