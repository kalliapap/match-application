package com.match.dev.service.impl;

import com.match.dev.dto.MatchOddsDto;
import com.match.dev.entity.Match;

import com.match.dev.repository.MatchOddsRepository;
import com.match.dev.service.MatchOddsService;

import java.util.List;

public class MatchOddsServiceImpl implements MatchOddsService {

    private final MatchOddsRepository matchOddsRepository;

    public MatchOddsServiceImpl(MatchOddsRepository matchOddsRepository) {
        this.matchOddsRepository = matchOddsRepository;
    }

    @Override
    public MatchOddsDto save(MatchOddsDto matchOddsDto) {
        return null;
    }

    @Override
    public List<MatchOddsDto> findAll() {
        return List.of();
    }

    @Override
    public boolean delete(Match match) {
        return false;
    }

    @Override
    public MatchOddsDto update(MatchOddsDto matchOddsDto) {
        return null;
    }

}
