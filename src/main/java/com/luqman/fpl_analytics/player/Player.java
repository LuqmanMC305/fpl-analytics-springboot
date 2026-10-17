/**
 * Player Entity
 * Describe What a Player Is
 */
package com.luqman.fpl_analytics.player;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name ="players")
public class Player{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true)
    private Integer fplId;

    private String webName;

    private String teamName;

    private String firstName;

    private String secondName;

    private String position;

    private Integer totalPoints;

    private Integer nowCost;

    private Integer goalsScored;

    private Integer assists;

    private BigDecimal selectedByPercent;

    private LocalDateTime lastUpdated;

}
