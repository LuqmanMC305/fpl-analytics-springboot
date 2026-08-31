package com.luqman.fpl_analytics.player;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;

     public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }
    
    @GetMapping
    public String getPlayer(
        @RequestParam String name,
        Model model) {

        String playerName = playerService.findPlayer(name);

        model.addAttribute("playerName", playerName);

        return "player/details";
    }
    
}
