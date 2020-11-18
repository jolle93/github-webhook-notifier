package de.paul.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.paul.dto.MailDTO;
import de.paul.rest.client.GithubService;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * author: JP
 * date: 31.05.20
 */
@ApplicationScoped
public class RuleService {
    public static final String PULL_REQUEST = "pull_request";
    public static final String REF_TYPE = "ref_type";
    public static final String TAG = "tag";
    public static final String EMAIL = "email";
    @Inject
    @RestClient
    GithubService githubService;

    @ConfigProperty(name = "github.user.access.token")
    String authToken;
    @ConfigProperty(name = "mail.fallback.address")
    String fallbackRecipient;

    @Inject
    CronEmailService cronEmailService;

    ObjectMapper objectMapper;

    @PostConstruct
    public void init() {
        objectMapper = new ObjectMapper();
    }

    public void decide(String payload) throws JsonProcessingException {
        //TODO find a better way to build the auth header
        JsonNode owner = objectMapper.readTree(githubService.getAuthenticatedUser("Bearer " + authToken));
        JsonNode githubPayload = objectMapper.readTree(payload);

        if (githubPayload.has(REF_TYPE) && githubPayload.get(REF_TYPE).asText().equals(TAG) && isSameUser(owner.get("id").asText(), githubPayload.get("sender").get("id").asText())) {
            cronEmailService.getEmails().add(
                    new MailDTO(owner.get(EMAIL).asText() != null ? owner.get(EMAIL).asText() : fallbackRecipient, "A new Tag", buildEmailBody(TAG, githubPayload)));
        }

        if (githubPayload.has(PULL_REQUEST) && isSameUser(owner.get("id").asText(), githubPayload.get("user").get("id").asText())) {
            cronEmailService.getEmails().add(
                    new MailDTO(owner.get(EMAIL).asText() != null ? owner.get(EMAIL).asText() : fallbackRecipient, "A new PR", buildEmailBody(PULL_REQUEST, githubPayload)));
        }
    }


    boolean isSameUser(String ownerId, String otherId) {
        return !ownerId.equalsIgnoreCase(otherId);
    }

    String buildEmailBody(String rule, JsonNode payload) {
        String body;
        switch (rule) {
            case PULL_REQUEST:
                body = buildPrMail(payload);
                break;
            case TAG:
                body = buildNewTag(payload);
                break;
            default:
                body = "";
        }
        return body;
    }

    String buildPrMail(JsonNode payload) {
        return "The User: " + payload.get(PULL_REQUEST).get("user").get("login").asText() + " opened a PR on " + payload.get(PULL_REQUEST).get("url");
    }

    String buildNewTag(JsonNode payload) {
        return "The User: " + payload.get("sender").get("login").asText() + " released a new Tag at Repo " + payload.get("repository").get("full_name");
    }
}
