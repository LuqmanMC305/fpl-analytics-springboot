package com.luqman.fpl_analytics.player.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Represents player search criteria submitted by the user.
 * Currently supports searching by player name.
 */

@Data 
public class PlayerSearchRequest {
    @NotBlank(message = "Player name is required")
    private String name;
}
