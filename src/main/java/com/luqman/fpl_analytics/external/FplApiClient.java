package com.luqman.fpl_analytics.external;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.luqman.fpl_analytics.external.fpl.dto.BootstrapResponse;

@Component 
public class FplApiClient {

    private final RestClient restClient = RestClient.create();
    
    
    public BootstrapResponse getBootstrapData(){

        return restClient.get()
        .uri("https://fantasy.premierleague.com/api/bootstrap-static/")
        .retrieve()
        .body(BootstrapResponse.class);
    }
}
