package com.match.dev.service.impl;

import com.match.dev.repository.MatchRepository;
import com.match.dev.service.MatchService;

public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;

    public MatchServiceImpl(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }


}
