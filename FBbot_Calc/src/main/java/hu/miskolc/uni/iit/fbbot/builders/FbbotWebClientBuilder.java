package hu.miskolc.uni.iit.fbbot.builders;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class FbbotWebClientBuilder {
    public WebClient getWebClient(String url){
        return WebClient
                .create(url);
    }
}
