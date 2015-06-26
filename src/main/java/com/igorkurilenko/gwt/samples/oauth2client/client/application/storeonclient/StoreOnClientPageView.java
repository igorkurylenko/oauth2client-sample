package com.igorkurilenko.gwt.samples.oauth2client.client.application.storeonclient;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class StoreOnClientPageView extends ViewWithUiHandlers<StoreOnClientPageUiHandlers> implements StoreOnClientPagePresenter.MyView {
    @UiField
    Button requestButton;
    @UiField
    Element howToCodeElement;
    @UiField
    Element callbackCodeElement;
    @UiField
    SpanElement tokenSpan;
    @UiField
    SpanElement expiresInSpan;

    interface Binder extends UiBinder<Widget, StoreOnClientPageView> {
    }

    @Inject
    StoreOnClientPageView(
            Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));

        initCodeElements();
    }

    private void initCodeElements() {
        howToCodeElement.setInnerText(HOW_TO_CODE);
        callbackCodeElement.setInnerText(CALLBACK_CODE);
    }

    @Override
    public void setTokenText(String token) {
        tokenSpan.setInnerText(token);
    }

    @Override
    public void setExpiresInText(String expiresIn) {
        expiresInSpan.setInnerText(expiresIn);
    }


    @UiHandler("requestButton")
    public void handleClick(ClickEvent event) {
        getUiHandlers().requestAccessToken();
    }

    private static final String HOW_TO_CODE = "// e.g.\n" +
            "String clientId =\n" +
            "        \"392293350498-7inmq35i0n9ofuckbm1ebd8fg18c270c.apps.googleusercontent.com\";\n" +
            "String redirectUri = \"http://localhost:8888/callback.html\";\n" +
            "String authEndpoint = \"https://accounts.google.com/o/oauth2/auth\";\n" +
            "Set<String> scopes = new HashSet<>();\n" +
            "scopes.add(\"https://www.googleapis.com/auth/youtube\");\n" +
            "\n" +
            "// instantiate request\n" +
            "AccessTokenRequest request = AccessTokenRequest.create(\n" +
            "        authEndpoint, clientId, redirectUri, scopes);\n" +
            "\n" +
            "// send request\n" +
            "ImplicitGrantOAuth2Client.getInstance()\n" +
            "        .retrieveAccessToken(request, new RetrieveAccessTokenCallback() {\n" +
            "            public void onFailure(Throwable reason) {\n" +
            "                ...\n" +
            "            }\n" +
            "\n" +
            "            public void onSuccess(AuthorizationResponse result) {\n" +
            "                ...\n" +
            "            }\n" +
            "        });";
    private static final String CALLBACK_CODE = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "    <script type=\"text/javascript\">\n" +
            "        window.opener.OAuth2Client.oauth2callback(location.hash);\n" +
            "    </script>\n" +
            "</head>\n" +
            "<body>\n" +
            "</body>\n" +
            "</html>";
}
