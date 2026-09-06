package com.luqman.fpl_analytics.player;

import org.springframework.stereotype.Service;

import com.luqman.fpl_analytics.external.FplApiClient;
import com.luqman.fpl_analytics.external.fpl.dto.BootstrapResponse;
import com.luqman.fpl_analytics.external.fpl.dto.PlayerDto;

@Service
public class PlayerService {

       private final FplApiClient fplApiClient;

       public PlayerService(FplApiClient fplApiClient) {
        this.fplApiClient = fplApiClient;
    }
    
        public PlayerDto findPlayer(String name){

            BootstrapResponse response = 
                fplApiClient.getBootstrapData();

            return response.getElements()
                .stream()
                .filter(p ->
                        p.getWebName()
                            .equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);


    }
}
