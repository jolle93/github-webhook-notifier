package de.paul.rest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.paul.rest.client.GithubService;
import de.paul.service.RuleService;
import io.quarkus.mailer.Mailer;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * author: JP
 *
 * This class consumes git hub web hooks.
 */
@Path("/notify")
public class WebHookService {


    @Inject
    RuleService ruleService;

    @Inject
    @RestClient
    GithubService githubService;

    @Inject
    Mailer mailer;
    ObjectMapper objectMapper = new ObjectMapper();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response getWebHookNotification(String payload) throws JsonProcessingException {

        ruleService.decide(payload);

        return Response.ok().build();
    }
}
