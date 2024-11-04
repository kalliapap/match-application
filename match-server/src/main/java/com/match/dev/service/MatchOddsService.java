package com.match.dev.service;

import com.match.dev.dto.MatchOddsDto;
import com.match.dev.entity.Match;

import java.util.List;

public interface MatchOddsService {

    MatchOddsDto save(MatchOddsDto matchOddsDto);

    List<MatchOddsDto> findAll();

    boolean delete(Match match);

    MatchOddsDto update(MatchOddsDto matchOddsDto);
}
