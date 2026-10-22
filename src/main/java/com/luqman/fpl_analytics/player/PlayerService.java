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
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.stream.Collectors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import java.util.function.Function;


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

            // Find if the player already exists in DB (cached player)
            Optional<Player> cachedPlayer = 
                playerRepository.findFirstByWebNameIgnoreCase(name);


            if(cachedPlayer.isPresent())
            {
                log.info("Player '{}' found in cache! ", name);

                if (!isPlayerDataStale(cachedPlayer.get()) &&
                    cachedPlayer.get().getSelectedByPercent() != null){
                         log.info("Player '{}' has fresh data!", name);
                         return toPlayerDto(cachedPlayer.get());
                    }
                else{
                        log.info("Player '{}' has stale data!", name);
                        
                }
            }
            else{
                log.info("Player '{}' not found in cache! ", name);
            }

            // If the player data is stale
            PlayerDto player = findPlayerInApi(name);
            if (player == null) return null;
            
            // Call the helper function
            refreshAndSavePlayer(player);

            return player;
    }

    // Helper method to convert from Player to PlayerDTO
    private PlayerDto toPlayerDto(Player player){
        PlayerDto dto = new PlayerDto();

        dto.setWebName(player.getWebName());
        dto.setTeamName(player.getTeamName());
        dto.setTotalPoints(player.getTotalPoints());
        dto.setTotalPoints(player.getTotalPoints());
        dto.setNowCost(player.getNowCost());
        dto.setGoalsScored(player.getGoalsScored());
        dto.setAssists(player.getAssists());
        dto.setSelectedByPercent(String.valueOf(player.getSelectedByPercent()));
        dto.setFirstName(player.getFirstName());


        return dto;
    }

    // Helper method to refresh & save player to DB
    private void refreshAndSavePlayer(PlayerDto dto){
         Player entity = playerRepository.findByFplId(dto.getId())
                .orElseGet(Player::new);

            entity.setFplId(dto.getId());
            entity.setWebName(dto.getWebName());
            entity.setTeamName(dto.getTeamName());
            entity.setTotalPoints(dto.getTotalPoints());
            entity.setNowCost(dto.getNowCost());
            entity.setGoalsScored(dto.getGoalsScored());
            entity.setAssists(dto.getAssists());
            entity.setSelectedByPercent(new BigDecimal(dto.getSelectedByPercent()));
            entity.setLastUpdated(LocalDateTime.now());
            entity.setFirstName(dto.getFirstName());
            /*
                FOR FUTURE PLAYER FIELDS
                entity.setPosition(player.getPosition());
            */   
            
            playerRepository.save(entity);

            log.info("Saved Player '{}' with FPL ID: {} to DB", 
                entity.getWebName(), entity.getFplId());
            
    }

    private PlayerDto findPlayerInApi(String name){
           BootstrapResponse response = 
                fplApiClient.getBootstrapData();

            if (response == null || response.getElements() == null){
                return null;
            }

            if(response.getTeams() == null){
                return null;
            }

             // Convert team list into a map for fast team ID -> team name lookup.
           Map<Integer, TeamDto> teamMap = 
                response.getTeams()
                        .stream()
                        .collect(Collectors.toMap(
                            TeamDto::getId, 
                            Function.identity(),
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
            
            // Retrieve the player's team using its FPL team ID
            TeamDto team = teamMap.get(player.getTeam());

            if(team != null){
                // Set player's team name
                player.setTeamName(team.getName());

                 // Generate the team's badge URL
                if(team.getCode() != null){
                    player.setTeamBadgeUrl(
                        "https://resources.premierleague.com/premierleague/badges/70/t"
                        + team.getCode() + ".png"
                    );
                }
            }

            return player;
    }

    // Helper method to determine if the player data is outdated
    private boolean isPlayerDataStale(Player player){
        LocalDateTime cutOffTime = LocalDateTime.now().minusHours(6);
        return (player.getLastUpdated() == null || player.getLastUpdated().isBefore(cutOffTime));

    }
}
