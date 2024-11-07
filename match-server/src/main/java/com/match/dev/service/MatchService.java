package com.match.dev.service;

import com.match.dev.dto.MatchDto;
import com.match.dev.dto.MatchResponseDto;
import com.match.dev.dto.MatchUpdateRequestDto;
import com.match.dev.exception.MatchAlreadyExistsException;
import com.match.dev.exception.MatchNotFoundException;
import com.match.dev.exception.MatchOddValidationFailedException;
import com.match.dev.exception.MatchServiceException;

import java.util.List;

public interface MatchService {

    MatchResponseDto createMatch(MatchDto matchDto) throws MatchAlreadyExistsException, MatchServiceException, MatchOddValidationFailedException;

    List<MatchDto> findAllMatches() throws MatchServiceException;

    MatchDto findMatchById(long id) throws MatchServiceException, MatchNotFoundException;

    void deleteMatch(long id) throws MatchNotFoundException, MatchServiceException;

    void updateMatch(MatchUpdateRequestDto newMatch) throws MatchNotFoundException, MatchServiceException, MatchOddValidationFailedException;
}
