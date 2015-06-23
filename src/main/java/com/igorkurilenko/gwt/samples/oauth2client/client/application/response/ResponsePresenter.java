package com.igorkurilenko.gwt.samples.oauth2client.client.application.response;

import javax.inject.Inject;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.igorkurilenko.gwt.samples.oauth2client.client.place.NameTokens;
import com.igorkurilenko.gwt.samples.oauth2client.client.place.TokenParameters;
import com.igorkurilenko.gwt.samples.oauth2client.shared.dispatch.SendTextToServerAction;
import com.igorkurilenko.gwt.samples.oauth2client.shared.dispatch.SendTextToServerResult;


public class ResponsePresenter
        extends Presenter<ResponsePresenter.MyView, ResponsePresenter.MyProxy>
        implements ResponseUiHandlers {
    @ProxyCodeSplit
    @NameToken(NameTokens.response)
    interface MyProxy extends ProxyPlace<ResponsePresenter> {
    }

    interface MyView extends View, HasUiHandlers<ResponseUiHandlers> {
        void setServerResponse(String serverResponse);

        void setTextToServer(String textToServer);
    }

    private final DispatchAsync dispatcher;
    private final PlaceManager placeManager;

    private String textToServer;

    @Inject
    ResponsePresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy,
            PlaceManager placeManager,
            DispatchAsync dispatcher) {
        super(eventBus, view, proxy, RevealType.Root);

        this.placeManager = placeManager;
        this.dispatcher = dispatcher;

        getView().setUiHandlers(this);
    }

    @Override
    public void prepareFromRequest(PlaceRequest request) {
        super.prepareFromRequest(request);

        textToServer = request.getParameter(TokenParameters.TEXT_TO_SERVER, null);
    }

    public void onClose() {
        PlaceRequest homePlaceRequest = new PlaceRequest.Builder().nameToken(NameTokens.home).build();
        placeManager.revealPlace(homePlaceRequest);
    }

    @Override
    protected void onReset() {
        super.onReset();

        getView().setTextToServer(textToServer);
        getView().setServerResponse("Waiting for response...");

        dispatcher.execute(new SendTextToServerAction(textToServer), new AsyncCallback<SendTextToServerResult>() {
            public void onFailure(Throwable caught) {
                getView().setServerResponse("An error occured: " + caught.getMessage());
            }

            public void onSuccess(SendTextToServerResult result) {
                getView().setServerResponse(result.getResponse());
            }
        });
    }
}
