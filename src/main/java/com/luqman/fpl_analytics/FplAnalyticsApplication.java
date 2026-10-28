package com.luqman.fpl_analytics;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.luqman.fpl_analytics.team.TeamService;
@SpringBootApplication
public class FplAnalyticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FplAnalyticsApplication.class, args);
	}

	/*Runs the syncTeams method automatically
	@Bean
	CommandLineRunner populateTeams(TeamService teamService){
		return args -> {
			teamService.syncTeams();
		};
	}
	*/

}
