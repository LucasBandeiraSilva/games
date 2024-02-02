package com.lucas.bandeira.games.games.repository;

import com.lucas.bandeira.games.games.entity.Games;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamesRepository extends JpaRepository<Games,Long> {
}
