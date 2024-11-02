package com.match.dev.service.impl;

import com.match.dev.repository.MatchOddsRepository;
import com.match.dev.service.MatchOddsService;

public class MatchOddsServiceImpl implements MatchOddsService {

    private final MatchOddsRepository matchOddsRepository;

    public MatchOddsServiceImpl(MatchOddsRepository matchOddsRepository) {
        this.matchOddsRepository = matchOddsRepository;
    }
}
