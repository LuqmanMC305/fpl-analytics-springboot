package com.luqman.fpl_analytics.team;

import org.springframework.stereotype.Service;

import com.luqman.fpl_analytics.external.FplApiClient;
import com.luqman.fpl_analytics.external.fpl.dto.BootstrapResponse;
import com.luqman.fpl_analytics.player.PlayerRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor 
public class TeamService {

    private final TeamRepository teamRepository;
    private final FplApiClient fplApiClient;

    public TeamService(FplApiClient fplApiClient, TeamRepository teamRepository) {
        this.fplApiClient = fplApiClient;
        this.teamRepository = teamRepository;
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
