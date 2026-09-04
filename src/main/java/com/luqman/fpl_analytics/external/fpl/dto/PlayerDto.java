package com.luqman.fpl_analytics.external.fpl.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerDto {
    private Integer id;
    private String web_name;
    private Integer team;
    private Integer now_cost;
    private Integer total_points;


}
