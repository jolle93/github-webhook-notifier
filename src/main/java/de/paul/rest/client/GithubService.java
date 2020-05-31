package de.paul.rest.client;

import io.quarkus.arc.config.ConfigProperties;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * author: JP
 * date: 31.05.20
 */
@Path("")
@RegisterRestClient(configKey="github-api")
public interface GithubService {

    @GET
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    String getAuthenticatedUser(@HeaderParam("Authorization") String auth);
}
