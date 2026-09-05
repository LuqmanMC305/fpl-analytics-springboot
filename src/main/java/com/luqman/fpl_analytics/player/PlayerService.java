package com.luqman.fpl_analytics.player;

import org.springframework.stereotype.Service;

import com.luqman.fpl_analytics.external.FplApiClient;
import com.luqman.fpl_analytics.external.fpl.dto.BootstrapResponse;

@Service
public class PlayerService {

       private final FplApiClient fplApiClient;

       public PlayerService(FplApiClient fplApiClient) {
        this.fplApiClient = fplApiClient;
    }
    
        public String findPlayer(String name){

            BootstrapResponse response = 
                fplApiClient.getBootstrapData();

            return response.getElements()
                .get(0) // Get first player
                .getWeb_name(); // Get player name
    }
}
