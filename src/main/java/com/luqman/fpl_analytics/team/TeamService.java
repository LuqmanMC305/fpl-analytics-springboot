package com.luqman.fpl_analytics.team;

import org.springframework.stereotype.Service;

import com.luqman.fpl_analytics.external.FplApiClient;
import com.luqman.fpl_analytics.external.fpl.dto.BootstrapResponse;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final FplApiClient fplApiClient;

    public TeamService(TeamRepository teamRepository, FplApiClient fplApiClient) {
        this.teamRepository = teamRepository;
        this.fplApiClient = fplApiClient;
    }

    public void syncTeams(){
        
        // Fetch FPL API response
        BootstrapResponse response = 
                fplApiClient.getBootstrapData();
        
        // Convert API DTOs to DB entities
        List<Team> teams = response.getTeams()
            .stream()
            .map(teamDto -> {
                Team team = new Team();

                team.setId(teamDto.getId());
                team.setName(teamDto.getName());
                team.setCode(teamDto.getCode());

                return team;
            })
            .toList();

        // Insert or updare team data in DB
        teamRepository.saveAll(teams);
    }
}
