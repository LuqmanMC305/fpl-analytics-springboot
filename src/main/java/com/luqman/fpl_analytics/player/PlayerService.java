package com.luqman.fpl_analytics.player;

/**
 * BUSINESS LOGIC
 * Service layer for player-related operations 
 * Retrieves FPL player data, performs player searches,
 * and prepares player information for presentation.
 */

import org.springframework.stereotype.Service;

import com.luqman.fpl_analytics.external.FplApiClient;
import com.luqman.fpl_analytics.external.fpl.dto.BootstrapResponse;
import com.luqman.fpl_analytics.external.fpl.dto.PlayerDto;
import com.luqman.fpl_analytics.external.fpl.dto.TeamDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.stream.Collectors;


@Service
public class PlayerService {

       private final PlayerRepository playerRepository;
       private final FplApiClient fplApiClient;

        private static final Logger log = 
            LoggerFactory.getLogger(PlayerService.class);

       public PlayerService(FplApiClient fplApiClient, PlayerRepository playerRepository) {
        this.fplApiClient = fplApiClient;
        this.playerRepository = playerRepository;
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
            
            if(player == null){
                return null;
            }
            
            // Get player's team ID
                player.setTeamName(
                    teamMap.get(player.getTeam())); // Find the team's name from map
            

            // Save the player's latest data to db
            Player entity = playerRepository.findByFplId(player.getId())
                .orElseGet(Player::new);

            entity.setFplId(player.getId());
            entity.setWebName(player.getWebName());
            entity.setTeamName(player.getTeamName());
            //entity.setPosition(player.getPosition());
            entity.setTotalPoints(player.getTotalPoints());
            entity.setNowCost(player.getNowCost());
            entity.setGoalsScored(player.getGoalsScored());
            entity.setAssists(player.getAssists());

            Player savedPlayer = playerRepository.save(entity);
            log.info("Saved Player '{}' with FPL ID: {}", 
                savedPlayer.getWebName(), savedPlayer.getFplId());

            return player;
    }
}
