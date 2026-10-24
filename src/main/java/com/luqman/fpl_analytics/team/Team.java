package com.luqman.fpl_analytics.team;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a Premier League club stored in PostgreSQL.
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name ="teams")
public class Team {
    
    @Id
    private Integer id; //FPL team ID

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer code; // Club badge code
}
