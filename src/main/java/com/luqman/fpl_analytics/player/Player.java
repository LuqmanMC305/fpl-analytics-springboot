/**
 * Player Entity
 * 
 */
package com.luqman.fpl_analytics.player;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name ="players")
public class Player{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private Long id;

    private String webName;

    private String team;

    private String position;

    private Integer totalPoints;

    private Integer nowCost;

    private Integer goalsScored;

    private Integer assists;


}
