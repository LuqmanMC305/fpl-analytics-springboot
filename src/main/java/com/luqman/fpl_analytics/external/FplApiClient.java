package com.luqman.fpl_analytics.external;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component 
public class FplApiClient {

    private final RestClient restClient = RestClient.create();
    
    public String getRawData(){
        return restClient.get()
        .uri("https://fantasy.premierleague.com/api/bootstrap-static/")
        .retrieve()
        .body(String.class);
    }
}
