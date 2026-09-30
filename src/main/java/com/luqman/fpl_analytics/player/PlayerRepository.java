/**
 * Repository layer for Player entities.
 * Provides database access and CRUD operations through Spring Data JPA.
 */

package com.luqman.fpl_analytics.player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository 
public interface PlayerRepository 
    extends JpaRepository<Player, Long> {
        //Finds a player using their ID from FPL API
        Optional<Player> findByFplId(Integer fplId);
}
