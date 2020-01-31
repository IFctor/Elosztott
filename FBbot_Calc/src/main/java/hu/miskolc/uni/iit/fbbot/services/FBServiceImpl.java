package hu.miskolc.uni.iit.fbbot.services;

import hu.miskolc.uni.iit.fbbot.builders.FbbotWebClientBuilder;
import hu.miskolc.uni.iit.fbbot.facebook.models.Event;
import hu.miskolc.uni.iit.fbbot.facebook.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FBServiceImpl implements FBService{

    private final RestTemplate restTemplate;

    private final FbWebRequest fbWebRequest;
    private final FbApiEndpoints fbApiEndpoints;
    private final FbbotWebClientBuilder webClientBuilder;

    public User getUser(String id, String pageAccessToken) {
        //return webClientBuilder.getWebClient(fbApiEndpoints.getUserApiUrl()).method(HttpMethod.GET).exchange().block().toEntity(User.class).block().getBody();
        return restTemplate.getForEntity(fbApiEndpoints.getUserApiUrl(), User.class, id, pageAccessToken).getBody();
    }

    public ResponseEntity<String> sendResponse(Event event) {
        try {
            return
                    fbWebRequest.getResponseEntity(event);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
        }
    }
}

