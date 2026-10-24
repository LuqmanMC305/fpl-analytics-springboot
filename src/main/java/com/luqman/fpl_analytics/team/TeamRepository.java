/**
 * Repository layer for Team entities.
 * Provides database access and CRUD operations through Spring Data JPA.
 */

package com.luqman.fpl_analytics.team;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository 
    extends JpaRepository<Team, Integer> {
    
}
