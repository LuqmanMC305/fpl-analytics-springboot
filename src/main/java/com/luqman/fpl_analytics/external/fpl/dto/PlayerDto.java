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

    @JsonProperty("now_cost")
    private Integer nowCost;

    @JsonProperty("total_points")
    private Integer totalPoints;


}
