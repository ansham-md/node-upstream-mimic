package com.amos.nodeupstream.web.config;

import com.amos.nodeupstream.web.HttpExchangeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {

    /* If the downstream is load balanced */
//    @Autowired
//    private LoadBalancedExchangeFilterFunction filterFunction;

    @Bean
    public WebClient enterpriseWebClient(){
        return WebClient.builder()
                //.baseUrl("http://host.docker.internal:8080")
                .baseUrl("http://localhost:8080")
                //.filter(filterFunction)
                .build();
    }

    @Bean
    public HttpExchangeHandler httpExchangeHandler(){
        HttpServiceProxyFactory httpServiceProxyFactory=
                HttpServiceProxyFactory
                        .builder(WebClientAdapter.forClient(enterpriseWebClient()))
                        .build();
        return httpServiceProxyFactory.createClient(HttpExchangeHandler.class);
    }
}
