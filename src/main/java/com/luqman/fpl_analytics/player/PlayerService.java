package com.luqman.fpl_analytics.player;

import org.springframework.stereotype.Service;

import com.luqman.fpl_analytics.external.FplApiClient;

@Service
public class PlayerService {

       private final FplApiClient fplApiClient;

       public PlayerService(FplApiClient fplApiClient) {
        this.fplApiClient = fplApiClient;
    }
    
        public String findPlayer(String name){
            return fplApiClient.getPlayerByName(name);
    }
}
