package com.igorkurilenko.gwt.samples.oauth2client.client.application.storeonclient;

import com.google.gwt.user.client.Timer;
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
import io.itdraft.gwt.oauth2.AccessTokenRequest;
import io.itdraft.gwt.oauth2.AuthorizationResponse;
import io.itdraft.gwt.oauth2.RetrieveAccessTokenCallback;
import io.itdraft.gwt.oauth2.implicit.ImplicitGrantOAuth2Client;

import java.util.HashSet;
import java.util.Set;

public class StoreOnClientPagePresenter
        extends Presenter<StoreOnClientPagePresenter.MyView, StoreOnClientPagePresenter.MyProxy>
        implements StoreOnClientPageUiHandlers {
    interface MyView extends View, HasUiHandlers<StoreOnClientPageUiHandlers> {
        void setTokenText(String token);

        void setExpiresInText(String expiresIn);
    }

    @ProxyStandard
    @NameToken(NameTokens.STORE_ON_CLIENT)
    @NoGatekeeper
    interface MyProxy extends ProxyPlace<StoreOnClientPagePresenter> {
    }

    private int expiresIn = 0;
    private Timer expiresInTimer = new Timer() {
        @Override
        public void run() {
            getView().setExpiresInText(--expiresIn + "");

            if (expiresIn <= 0) {
                cancel();
            }
        }
    };

    @Inject
    StoreOnClientPagePresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN_CONTENT);

        view.setUiHandlers(this);
    }

    public void requestAccessToken() {
        // e.g.
        String clientId =
                "392293350498-7inmq35i0n9ofuckbm1ebd8fg18c270c.apps.googleusercontent.com";
        String redirectUri = "http://localhost:8888/callback.html";
        String authEndpoint = "https://accounts.google.com/o/oauth2/auth";
        Set<String> scopes = new HashSet<>();
        scopes.add("https://www.googleapis.com/auth/youtube");

        // instantiate request
        AccessTokenRequest request = AccessTokenRequest.create(
                authEndpoint, clientId, redirectUri, scopes);

        // send request
        ImplicitGrantOAuth2Client.getInstance()
                .retrieveAccessToken(request, new RetrieveAccessTokenCallback() {
                    public void onFailure(Throwable reason) {
                        Window.alert(reason.getMessage());
                    }

                    public void onSuccess(AuthorizationResponse result) {
                        getView().setTokenText(result.getAccessToken());
                        getView().setExpiresInText(result.getExpiresIn());

                        expiresIn = Integer.parseInt(result.getExpiresIn());
                        expiresInTimer.scheduleRepeating(1000);
                    }
                });
    }
}
