package com.luqman.fpl_analytics.player;

/**
 * Handles player-related web requests.
 * Receives user search input, delegates processing to the service layer,
 * and returns player data to Thymeleaf views.
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.luqman.fpl_analytics.external.fpl.dto.PlayerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;

    private static final Logger log = 
        LoggerFactory.getLogger(PlayerService.class);

     public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }
    
    @GetMapping
    public String getPlayer(
        @RequestParam String name,
        Model model) {

        PlayerDto player = playerService.findPlayer(name);
        log.info("Search for player: {}", name);

        // Logging for finding player
        if(name == null || name.isBlank()){
            log.warn("\"Player search attempted with empty name");
            return null;
        }
        else{
            log.info("Player found: {}", player.getWebName());
        }

        model.addAttribute("player", player);

        return "player/details";
    }
    
}
