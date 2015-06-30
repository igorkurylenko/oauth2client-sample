package com.igorkurilenko.gwt.samples.oauth2client.client.application.storeonclient;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.igorkurilenko.gwt.samples.oauth2client.client.application.ApplicationPresenter;
import com.igorkurilenko.gwt.samples.oauth2client.client.place.NameTokens;
import io.itdraft.gwt.oauth2.AccessToken;
import io.itdraft.gwt.oauth2.AccessTokenCallback;
import io.itdraft.gwt.oauth2.FailureReason;
import io.itdraft.gwt.oauth2.OAuth2Client;
import io.itdraft.gwt.oauth2.implicit.ImplicitGrantOAuth2Client;

import java.util.HashSet;
import java.util.Set;

public class StoreOnClientPagePresenter
        extends Presenter<StoreOnClientPagePresenter.MyView, StoreOnClientPagePresenter.MyProxy>
        implements StoreOnClientPageUiHandlers {
    private static final String EXPIRED = "expired";
    private static final String CLIENT_ID =
            "392293350498-7inmq35i0n9ofuckbm1ebd8fg18c270c.apps.googleusercontent.com";

    interface MyView extends View, HasUiHandlers<StoreOnClientPageUiHandlers> {
        void setTokenText(String token);

        void setExpiresInText(String expiresIn);

        void setRefreshButtonVisible(boolean visible);
    }

    @ProxyStandard
    @NameToken(NameTokens.STORE_ON_CLIENT)
    @NoGatekeeper
    interface MyProxy extends ProxyPlace<StoreOnClientPagePresenter> {
    }

    @Inject
    StoreOnClientPagePresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN_CONTENT);

        view.setUiHandlers(this);

        Scheduler.get().scheduleFixedPeriod(new Scheduler.RepeatingCommand() {
            public boolean execute() {
                OAuth2Client oAuth2Client = ImplicitGrantOAuth2Client.get(CLIENT_ID);

                if (oAuth2Client != null) {
                    oAuth2Client.requestAccessToken(new AccessTokenCallback() {
                        protected void doOnFailure(FailureReason reason) {
                        }

                        protected void doOnSuccess(AccessToken token) {
                            renderToken(token);
                        }
                    });
                }

                return true;
            }
        }, 1000);
    }

    private void renderToken(AccessToken token) {
        getView().setTokenText(token.getToken());
        updateExpiresInText(token);
    }

    private void updateExpiresInText(AccessToken token) {
        long timeLeftInSeconds = token.getTimeLeftInSeconds();
        getView().setExpiresInText(timeLeftInSeconds == 0 ?
                EXPIRED : timeLeftInSeconds + "");
    }

    public void requestAccessToken() {
        // e.g.
        String clientId =
                "392293350498-7inmq35i0n9ofuckbm1ebd8fg18c270c.apps.googleusercontent.com";
        String redirectUri = "http://localhost:8888/callback.html";
        String authEndpoint = "https://accounts.google.com/o/oauth2/auth";
        Set<String> scopes = new HashSet<>();
        scopes.add("https://www.googleapis.com/auth/youtube");

        // send request
        ImplicitGrantOAuth2Client.create(clientId, redirectUri, authEndpoint, scopes)
                .requestAccessToken(new AccessTokenCallback() {
                    @Override
                    protected void doOnFailure(FailureReason reason) {
                        Window.alert(reason.getClass().getName());
                    }

                    @Override
                    protected void doOnSuccess(AccessToken token) {
                        renderToken(token);
                        getView().setRefreshButtonVisible(true);
                    }
                });
    }

    @Override
    public void refreshAccessToken() {
        String clientId =
                "392293350498-7inmq35i0n9ofuckbm1ebd8fg18c270c.apps.googleusercontent.com";
        // Once client is created you can use get an instance by clientId
        ImplicitGrantOAuth2Client.get(clientId).refreshAccessToken(new AccessTokenCallback() {
            @Override
            protected void doOnFailure(FailureReason reason) {
                Window.alert(reason.getClass().getName());
            }

            @Override
            protected void doOnSuccess(AccessToken token) {
                renderToken(token);
            }
        });
    }


}
