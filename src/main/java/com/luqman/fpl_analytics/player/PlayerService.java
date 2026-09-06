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
    
        public String findPlayer(String name){

            BootstrapResponse response = 
                fplApiClient.getBootstrapData();

            PlayerDto player = response.getElements()
                .stream()
                .filter(p ->
                        p.getWeb_name()
                            .equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);

            return player == null ? "Not Found" : player.getWeb_name();

    }
}
