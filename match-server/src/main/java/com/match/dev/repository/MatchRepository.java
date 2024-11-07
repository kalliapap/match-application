package com.match.dev.repository;

import com.match.dev.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    Optional<Match> findByTeamAAndTeamBAndMatchDateAndSport(String teamA, String teamB, LocalDate date, int sport);
}
