package com.match.dev.repository;

import com.match.dev.entity.MatchOdds;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchOddsRepository extends JpaRepository<MatchOdds, Long> {
}
