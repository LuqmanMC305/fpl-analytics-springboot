package com.luqman.fpl_analytics.player.dto;

import lombok.Data;

/**
 * Represents an FPL team from the bootstrap-static API.
 */
@Data
public class TeamDto {

    private Integer id;

    private String name;
}