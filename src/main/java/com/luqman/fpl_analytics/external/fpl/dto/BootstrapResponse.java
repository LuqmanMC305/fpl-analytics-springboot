package com.luqman.fpl_analytics.external.fpl.dto;

/**
 * Represents top-level response returned by FPL
 * bootstrap-static API endpoint
 * Contains player and team information
 */

import lombok.Setter;
import lombok.Getter;
import java.util.List;

@Getter
@Setter 
public class BootstrapResponse {

    private List<PlayerDto> elements;

     private List<TeamDto> teams;


}
