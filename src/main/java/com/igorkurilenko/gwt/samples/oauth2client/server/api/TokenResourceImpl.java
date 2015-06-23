package com.igorkurilenko.gwt.samples.oauth2client.server.api;

import com.igorkurilenko.gwt.samples.oauth2client.shared.api.TokenResource;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

public class TokenResourceImpl implements TokenResource {

    public String get(@Context UriInfo uriInfo) {
        String token = uriInfo.getRequestUri().getQuery();

        System.out.println(String.format("Caught token: %s", token));

        return "Successfully caught";
    }
}
