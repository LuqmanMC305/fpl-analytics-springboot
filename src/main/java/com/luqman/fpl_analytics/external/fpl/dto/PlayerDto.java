package com.luqman.fpl_analytics.external.fpl.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerDto {

    private Integer id;

    @JsonProperty("web_name")
    private String webName;

    private Integer team;

    @JsonProperty ("team_name")
    private String teamName;

    @JsonProperty("now_cost")
    private Integer nowCost;

    @JsonProperty("total_points")
    private Integer totalPoints;

    @JsonProperty ("goals_scored")
    private Integer goalsScored;

    @JsonProperty("assists")
    private Integer assists;

    @JsonProperty ("selected_by_percent")
    private String selectedByPercent;

    @JsonProperty("form")
    private String form;



}
