package com.igorkurilenko.gwt.samples.oauth2client.client.application.storeonclient;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class StoreOnClientPageView extends ViewWithUiHandlers<StoreOnClientPageUiHandlers>
        implements StoreOnClientPagePresenter.MyView {
    @UiField
    Button retrieveAccessTokenButton;
    @UiField
    Element howToRetrieveCodeElement;
    @UiField
    Element callbackCodeElement;
    @UiField
    SpanElement tokenSpan;
    @UiField
    SpanElement expiresInSpan;
    @UiField
    Button refreshAccessTokenButton;
    @UiField
    DivElement extraWhiteSpace;
    @UiField
    Element howToRefreshCodeElement;

    interface Binder extends UiBinder<Widget, StoreOnClientPageView> {
    }

    @Inject
    StoreOnClientPageView(
            Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));

        initCodeElements();
    }

    private void initCodeElements() {
        howToRetrieveCodeElement.setInnerText(HOW_TO_RETRIEVE_CODE);
        howToRefreshCodeElement.setInnerText(HOW_TO_REFRESH);
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

    @Override
    public void setRefreshButtonVisible(boolean visible) {
        extraWhiteSpace.getStyle().setDisplay(visible ? Style.Display.NONE : Style.Display.BLOCK);
        refreshAccessTokenButton.setVisible(visible);
    }


    @UiHandler("retrieveAccessTokenButton")
    public void handleRetrieveClick(ClickEvent event) {
        getUiHandlers().retrieveAccessToken();
    }

    @UiHandler("refreshAccessTokenButton")
    public void handleRefreshClick(ClickEvent event) {
        getUiHandlers().refreshAccessToken();
    }

    private static final String HOW_TO_RETRIEVE_CODE = "// e.g.\n" +
            "String clientId =\n" +
            "        \"392293350498-7inmq35i0n9ofuckbm1ebd8fg18c270c.apps.googleusercontent.com\";\n" +
            "String redirectUri = \"http://localhost:8888/callback.html\";\n" +
            "String authEndpoint = \"https://accounts.google.com/o/oauth2/auth\";\n" +
            "Set<String> scopes = new HashSet<>();\n" +
            "scopes.add(\"https://www.googleapis.com/auth/youtube\");\n" +
            "\n" +
            "// send request\n" +
            "ImplicitGrantOAuth2Client.create(clientId, redirectUri, authEndpoint, scopes)\n" +
            "        .retrieveAccessToken(new AccessTokenCallback() {\n" +
            "            @Override\n" +
            "            protected void doOnFailure(FailureReason reason) {\n" +
            "                ...\n" +
            "            }\n" +
            "\n" +
            "            @Override\n" +
            "            protected void doOnSuccess(AccessToken token) {\n" +
            "                ...\n" +
            "            }\n" +
            "        });";
    private static final String CALLBACK_CODE = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "    <script type=\"text/javascript\">\n" +
            "        var parent = window.opener || window.parent;\n" +
            "        parent.OAuth2Client.oauth2callback(location.hash);\n" +
            "    </script>\n" +
            "</head>\n" +
            "<body>\n" +
            "</body>\n" +
            "</html>";
    private static final String HOW_TO_REFRESH = "String clientId =\n" +
            "        \"392293350498-7inmq35i0n9ofuckbm1ebd8fg18c270c.apps.googleusercontent.com\";\n" +
            "\n" +
            "ImplicitGrantOAuth2Client.get(clientId).refreshAccessToken(new AccessTokenCallback() {\n" +
            "    @Override\n" +
            "    protected void doOnFailure(FailureReason reason) {\n" +
            "        ...\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    protected void doOnSuccess(AccessToken token) {\n" +
            "        ...\n" +
            "    }\n" +
            "});";
}
