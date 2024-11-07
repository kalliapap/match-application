package com.match.dev.service.impl;

import com.match.dev.dto.MatchDto;
import com.match.dev.dto.MatchOddDto;
import com.match.dev.dto.MatchResponseDto;
import com.match.dev.dto.MatchUpdateRequestDto;
import com.match.dev.entity.Match;
import com.match.dev.entity.MatchOdds;
import com.match.dev.exception.*;
import com.match.dev.repository.MatchOddsRepository;
import com.match.dev.repository.MatchRepository;
import com.match.dev.service.MatchService;
import com.match.dev.util.MatchUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchServiceImpl implements MatchService {

    private final Logger log = LoggerFactory.getLogger(MatchServiceImpl.class);

    private final MatchRepository matchRepository;
    private final MatchOddsRepository matchOddsRepository;


    public MatchServiceImpl(MatchRepository matchRepository, MatchOddsRepository matchOddsRepository) {
        this.matchRepository = matchRepository;
        this.matchOddsRepository = matchOddsRepository;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public MatchResponseDto createMatch(MatchDto matchDto) throws MatchAlreadyExistsException, MatchServiceException, MatchOddValidationFailedException {

        try {
            if (this.matchRepository.findByTeamAAndTeamBAndMatchDateAndSport(matchDto.getTeamA(), matchDto.getTeamB(),
                    matchDto.getMatchDate(), matchDto.getSport().getDbValue()).isPresent()) {
                throw new MatchAlreadyExistsException("Creation failed. Match already exists!");
            }

            MatchUtils.validateOdds(matchDto.getMatchOdds());
            Match match = MatchUtils.toMatchEntity(matchDto);
            return MatchResponseDto.from(this.matchRepository.save(match));

        } catch (MatchAlreadyExistsException e) {
            throw new MatchAlreadyExistsException(e.getMessage());
        } catch (MatchOddValidationFailedException e) {
            throw new MatchOddValidationFailedException(e.getMessage());
        } catch (Exception e) {
            log.error("Failed to create match: {}", e.getMessage());
            throw new MatchServiceException();
        }
    }

    @Override
    public List<MatchDto> findAllMatches() throws MatchServiceException {
        try {

            List<Match> matches = matchRepository.findAll();

            return matches.stream()
                    .map(match -> new MatchDto.Builder()
                            .withId(match.getId())
                            .withDescription(match.getDescription())
                            .withMatchDate(match.getMatchDate())
                            .withMatchTime(match.getMatchTime())
                            .withTeamA(match.getTeamA())
                            .withTeamB(match.getTeamB())
                            .withSport(match.getSport())
                            .withMatchOdds(match.getMatchOdds().stream()
                                    .map(MatchUtils::toMatchOddsDto)
                                    .collect(Collectors.toList())
                            )
                            .build()
                    )
                    .collect(Collectors.toList());

        } catch (Exception e) {
            log.error("Failed to fetch all matches: {}", e.getMessage());
            throw new MatchServiceException();
        }
    }

    @Override
    public MatchDto findMatchById(long id) throws MatchServiceException, MatchNotFoundException {
        try {
            Match match = this.matchRepository.findById(id)
                    .orElseThrow(() -> new MatchNotFoundException("Match not found with id: " + id));
            return MatchUtils.toMatchDto(match);
        } catch (MatchNotFoundException e) {
            throw new MatchNotFoundException(e.getMessage());
        } catch (Exception e) {
            log.error("Failed to find match: {}", e.getMessage());
            throw new MatchServiceException();
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMatch(long id) throws MatchNotFoundException, MatchServiceException {
        try {
            Match match = this.matchRepository.findById(id)
                    .orElseThrow(() -> new MatchNotFoundException("Match not found with id: " + id));

            this.matchRepository.delete(match);
        } catch (MatchNotFoundException e) {
            throw new MatchNotFoundException(e.getMessage());
        } catch (Exception e) {
            log.error("Failed to delete match: {}", e.getMessage());
            throw new MatchServiceException();
        }
    }

    @Override
    @Transactional
    public void updateMatch(MatchUpdateRequestDto newMatch) throws MatchNotFoundException, MatchServiceException, MatchOddValidationFailedException {
        try {
            Match match = this.matchRepository.findById(newMatch.getId())
                    .orElseThrow(() -> new MatchNotFoundException("Match not found with id: " + newMatch.getId()));

            Match updatedMatch = this.updateEntity(match, newMatch);

            matchRepository.save(updatedMatch);
        } catch (MatchNotFoundException e) {
            throw new MatchNotFoundException(e.getMessage());
        } catch (MatchOddValidationFailedException e) {
            throw new MatchOddValidationFailedException(e.getMessage());
        } catch (Exception e) {
            log.error("Failed to update match: {}", e.getMessage());
            throw new MatchServiceException();
        }
    }

        private Match updateEntity(Match oldMatch, MatchUpdateRequestDto newMatch) throws MatchOddNotFoundException, MatchOddValidationFailedException {
        Match match = new Match();

        match.setId(oldMatch.getId());
        match.setDescription(StringUtils.isNotEmpty(newMatch.getDescription()) ? newMatch.getDescription() : oldMatch.getDescription());
        match.setMatchDate(newMatch.getMatchDate() != null ? newMatch.getMatchDate() : oldMatch.getMatchDate());
        match.setMatchTime(newMatch.getMatchTime() != null ? newMatch.getMatchTime() : oldMatch.getMatchTime());
        match.setTeamA(StringUtils.isNotEmpty(newMatch.getTeamA()) ? newMatch.getTeamA() : oldMatch.getTeamA());
        match.setTeamB(StringUtils.isNotEmpty(newMatch.getTeamB()) ? newMatch.getTeamB() : oldMatch.getTeamB());
        match.setSport(newMatch.getSport() != null ? newMatch.getSport() : oldMatch.getSport());
        match.setMatchOdds(newMatch.getMatchOdds() != null && !newMatch.getMatchOdds().isEmpty() ?
                updateMatchOdds(oldMatch.getId(), newMatch.getMatchOdds()) : oldMatch.getMatchOdds());

        return match;
    }

    private List<MatchOdds> updateMatchOdds(long matchId, List<MatchOddDto> matchOddDtoList) throws MatchOddNotFoundException, MatchOddValidationFailedException {
        List<MatchOdds> odds = new ArrayList<>();
        for (MatchOddDto odd : matchOddDtoList) {
            MatchOdds updatedMatchOdds = new MatchOdds();
            MatchOdds matchOdds = this.matchOddsRepository.findById(odd.getId())
                    .orElseThrow(() -> new MatchOddNotFoundException("Match odd not found with id: " + odd.getId()));
            if (matchOdds.getMatch().getId() == matchId) {
                if (MatchUtils.isSpecifierValid(odd.getSpecifier())) {
                    updatedMatchOdds.setSpecifier(odd.getSpecifier());
                } else {
                    throw new MatchOddValidationFailedException("Invalid specifier!");
                }
                if (odd.getOdd() > 0) {
                    updatedMatchOdds.setOdd(odd.getOdd());
                } else {
                    throw new MatchOddValidationFailedException("Odd must be positive!");
                }
                updatedMatchOdds.setMatch(odd.getMatch());
                updatedMatchOdds.setId(odd.getId());
            }
            odds.add(updatedMatchOdds);
        }
        return odds;
    }

}
