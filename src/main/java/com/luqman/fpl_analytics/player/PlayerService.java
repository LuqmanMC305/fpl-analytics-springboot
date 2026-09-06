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

            if(name == null || name.isBlank()){
                return null;
            }

            BootstrapResponse response = 
                fplApiClient.getBootstrapData();

            if (response == null || response.getElements() == null){
                return null;
            }

            return response.getElements()
                .stream()
                .filter(p ->
                        p.getWebName()
                            .equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);


    }
}
