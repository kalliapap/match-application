package com.match.dev.config;

import com.match.dev.repository.MatchOddsRepository;
import com.match.dev.repository.MatchRepository;
import com.match.dev.service.MatchOddsService;
import com.match.dev.service.MatchService;
import com.match.dev.service.impl.MatchOddsServiceImpl;
import com.match.dev.service.impl.MatchServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MatchServiceConfiguration {

    @Bean
    public MatchService matchService(MatchRepository matchRepository, MatchOddsRepository matchOddsRepository) {
        return new MatchServiceImpl(matchRepository, matchOddsRepository);
    }

    @Bean
    public MatchOddsService matchOddsService(MatchOddsRepository matchOddsRepository) {
        return new MatchOddsServiceImpl(matchOddsRepository);
    }

}
