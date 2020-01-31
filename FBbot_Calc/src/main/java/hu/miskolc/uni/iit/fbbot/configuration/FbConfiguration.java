package hu.miskolc.uni.iit.fbbot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class FbConfiguration  {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
