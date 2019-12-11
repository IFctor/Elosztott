package hu.miskolc.uni.iit.cloud.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(useDefaultFilters = false)
public class DemoApplication {


    @Value("${account.service.url}")
    private String accountServiceUrl;

    @LoadBalanced
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public MainController mainController(){
        return new MainController(webClientBuilder().baseUrl(accountServiceUrl).build());
    }
    public static void main(String[] args) {
        System.setProperty("spring.config.name","demo-server");
        SpringApplication.run(DemoApplication.class, args);
    }

}
