package hu.miskolc.uni.iit.fbbot.services;

import hu.miskolc.uni.iit.fbbot.builders.FbbotWebClientBuilder;
import hu.miskolc.uni.iit.fbbot.facebook.models.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FbWebRequestImpl implements FbWebRequest {

    private final FbApiEndpoints fbApiEndpoints;
    private final FbbotWebClientBuilder webClientBuilder;

    public ResponseEntity<String> getResponseEntity(Event event) {
        return webClientBuilder
                .getWebClient(fbApiEndpoints.getFbSendUrl())
                .method(HttpMethod.POST)
                .body(event, Event.class)
                .retrieve()
                .toEntity(String.class)
                .block();
    }
}
