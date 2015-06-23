package com.igorkurilenko.gwt.samples.oauth2client.shared.dispatch;

import com.gwtplatform.dispatch.rpc.shared.UnsecuredActionImpl;


public class SendTextToServerAction extends UnsecuredActionImpl<SendTextToServerResult> {
    private String textToServer;

    public SendTextToServerAction(String textToServer) {
        this.textToServer = textToServer;
    }

    @SuppressWarnings("unused")
    private SendTextToServerAction() {
    }

    public String getTextToServer() {
        return textToServer;
    }
}
