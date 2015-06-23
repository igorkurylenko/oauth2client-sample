package com.igorkurilenko.gwt.samples.oauth2client.shared.api;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path(ApiPaths.CATCH_TOKEN)
@Produces(MediaType.TEXT_PLAIN)
public interface TokenResource {

    @GET
    String get(@Context UriInfo uriInfo);

}
