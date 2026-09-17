package com.luqman.fpl_analytics.player;

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



            /* Temporarily ensure the team is populated
            for(TeamDto team : response.getTeams())
                System.out.println(team.getId() + " - " + team.getName());
           
            System.out.println("Total Teams " + response.getTeams().size());
           */ 

           // Convert team list into a map for fast team ID -> team name lookup.
           Map<Integer, String> teamMap = 
                response.getTeams()
                        .stream()
                        .collect(Collectors.toMap(
                            TeamDto::getId, 
                            TeamDto::getName,
                            (oldValue, newValue) -> oldValue // prevents duplicates, using parameter names of lambda expression
                        ));
            
            System.out.println(teamMap.get(1));
            

            return response.getElements()
                .stream()
                .filter(p ->
                        p.getWebName()
                            .equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);


    }
}
