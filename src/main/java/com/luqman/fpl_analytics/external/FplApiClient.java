package com.luqman.fpl_analytics.external;

import org.springframework.stereotype.Component;

@Component 
public class FplApiClient {
    
    public String getPlayerByName(String name){
        return "Mock Player" + name;
    }
}
