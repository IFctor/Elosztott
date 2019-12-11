package hu.miskolc.uni.iit.cloud.demo;

import hu.miskolc.uni.iit.cloud.accounts.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.MediaType;


@RestController
@RequiredArgsConstructor
public class MainController {

    private final WebClient webClient;


    @GetMapping(value = "eletErtelme", produces = MediaType.APPLICATION_JSON_VALUE)
    Eredmeny eletErtelme(){
        Result result = webClient.get()
                .uri("/mennyi")
                .retrieve()
                .bodyToMono(Result.class)
                .block();
        return new Eredmeny(String.format("Az élet értelme: %s", result.getResult()));
    }
}

