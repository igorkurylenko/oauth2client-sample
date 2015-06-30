package com.igorkurilenko.gwt.samples.oauth2client.client.application.storeonclient;

import com.gwtplatform.mvp.client.UiHandlers;

public interface StoreOnClientPageUiHandlers extends UiHandlers {

    void retrieveAccessToken();

    void refreshAccessToken();

}
