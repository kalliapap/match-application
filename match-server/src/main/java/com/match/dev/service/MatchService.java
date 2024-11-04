package com.match.dev.service;

import com.match.dev.dto.MatchDto;
import com.match.dev.dto.MatchResponseDto;
import com.match.dev.dto.MatchUpdateRequestDto;
import com.match.dev.exception.MatchAlreadyExistsException;
import com.match.dev.exception.MatchNotFoundException;
import com.match.dev.exception.MatchServiceException;

import java.util.List;

public interface MatchService {

    MatchResponseDto createMatch(MatchDto matchDto) throws MatchAlreadyExistsException, MatchServiceException;

    List<MatchDto> findAllMatches() throws MatchServiceException;

    MatchDto findMatchById(long id) throws MatchServiceException, MatchNotFoundException;

    boolean deleteMatch(long id) throws MatchNotFoundException, MatchServiceException;

    MatchDto updateMatch(MatchUpdateRequestDto newMatch) throws MatchNotFoundException, MatchServiceException;
}
