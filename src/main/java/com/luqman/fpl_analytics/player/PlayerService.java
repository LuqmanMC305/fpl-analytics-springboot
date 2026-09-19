package com.luqman.fpl_analytics.player;

/**
 * Service layer for player-related operations.
 * Retrieves FPL player data, performs player searches,
 * and prepares player information for presentation.
 */

import org.springframework.stereotype.Service;

import com.luqman.fpl_analytics.external.FplApiClient;
import com.luqman.fpl_analytics.external.fpl.dto.BootstrapResponse;
import com.luqman.fpl_analytics.external.fpl.dto.PlayerDto;
import com.luqman.fpl_analytics.external.fpl.dto.TeamDto;

import java.util.Map;
import java.util.stream.Collectors;


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

            if(response.getTeams() == null){
                return null;
            }

             // Convert team list into a map for fast team ID -> team name lookup.
           Map<Integer, String> teamMap = 
                response.getTeams()
                        .stream()
                        .collect(Collectors.toMap(
                            TeamDto::getId, 
                            TeamDto::getName,
                            (oldValue, newValue) -> oldValue // prevents duplicates, using parameter names of lambda expression
                        ));

            // Search the matching player from whole list
             PlayerDto player = response.getElements() 
                .stream()
                .filter(p -> 
                        p.getWebName()
                            .equalsIgnoreCase(name))
                .findFirst() // Return first match
                .orElse(null);
            
            // Get player's team ID
            if (player != null){
                player.setTeamName(
                    teamMap.get(player.getTeam())); // Find the team's name from map
            }

            return player;
    }
}
