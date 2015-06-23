package com.igorkurilenko.gwt.samples.oauth2client.server.dispatch;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;
import com.igorkurilenko.gwt.samples.oauth2client.shared.FieldVerifier;
import com.igorkurilenko.gwt.samples.oauth2client.shared.dispatch.SendTextToServerAction;
import com.igorkurilenko.gwt.samples.oauth2client.shared.dispatch.SendTextToServerResult;


public class SendTextToServerHandler implements ActionHandler<SendTextToServerAction, SendTextToServerResult> {
    private Provider<HttpServletRequest> requestProvider;
    private ServletContext servletContext;

    @Inject
    SendTextToServerHandler(
            ServletContext servletContext,
            Provider<HttpServletRequest> requestProvider) {
        this.servletContext = servletContext;
        this.requestProvider = requestProvider;
    }

    public SendTextToServerResult execute(SendTextToServerAction action, ExecutionContext context)
            throws ActionException {
        String input = action.getTextToServer();

        // Verify that the input is valid.
        if (!FieldVerifier.isValidName(input)) {
            // If the input is not valid, throw an IllegalArgumentException back to the client.
            throw new ActionException("Name must be at least 4 characters long.");
        }

        String serverInfo = servletContext.getServerInfo();
        String userAgent = requestProvider.get().getHeader("User-Agent");
        String response = String.format("Hello, %s!<br/><br/>I am running %s.<br/><br/>" +
                        "It looks like you are using:<br/>%s",
                input, serverInfo, userAgent);

        return new SendTextToServerResult(response);
    }

    public Class<SendTextToServerAction> getActionType() {
        return SendTextToServerAction.class;
    }

    public void undo(SendTextToServerAction action, SendTextToServerResult result, ExecutionContext context)
            throws ActionException {
        // Not undoable
    }
}
