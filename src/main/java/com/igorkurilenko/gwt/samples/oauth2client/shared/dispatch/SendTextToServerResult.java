package com.igorkurilenko.gwt.samples.oauth2client.shared.dispatch;

import com.gwtplatform.dispatch.rpc.shared.Result;


public class SendTextToServerResult implements Result {
    private String response;

    public SendTextToServerResult(final String response) {
        this.response = response;
    }

    @SuppressWarnings("unused")
    private SendTextToServerResult() {
    }

    public String getResponse() {
        return response;
    }
}
