package com.match.dev.service;

import com.match.dev.dto.MatchOddDto;
import com.match.dev.dto.MatchOddRequestDto;
import com.match.dev.exception.MatchNotFoundException;
import com.match.dev.exception.MatchOddNotFoundException;
import com.match.dev.exception.MatchServiceException;

import java.util.List;

public interface MatchOddsService {

    MatchOddDto createOdd(MatchOddRequestDto matchOddRequestDto) throws MatchNotFoundException, MatchServiceException;

    List<MatchOddDto> findAll() throws MatchServiceException;

    void deleteOdd(long id) throws MatchServiceException, MatchOddNotFoundException;

    void updateOdd(MatchOddDto matchOddDto) throws MatchOddNotFoundException, MatchServiceException;
}
