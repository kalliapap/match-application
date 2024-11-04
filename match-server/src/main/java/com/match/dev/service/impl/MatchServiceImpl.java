package com.match.dev.service.impl;

import com.match.dev.dto.MatchOddsDto;
import com.match.dev.dto.MatchDto;
import com.match.dev.dto.MatchResponseDto;
import com.match.dev.dto.MatchUpdateRequestDto;
import com.match.dev.entity.Match;
import com.match.dev.entity.MatchOdds;
import com.match.dev.exception.MatchAlreadyExistsException;
import com.match.dev.exception.MatchNotFoundException;
import com.match.dev.exception.MatchServiceException;
import com.match.dev.repository.MatchOddsRepository;
import com.match.dev.repository.MatchRepository;
import com.match.dev.service.MatchService;
import com.match.dev.validator.CommonValidator;
import jakarta.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


public class MatchServiceImpl implements MatchService, CommonValidator<Match> {

    Logger log = LoggerFactory.getLogger(MatchServiceImpl.class);

    private final MatchRepository matchRepository;
    private final MatchOddsRepository matchOddsRepository;

    @Autowired
    private Validator validator;

    public MatchServiceImpl(MatchRepository matchRepository, MatchOddsRepository matchOddsRepository) {
        this.matchRepository = matchRepository;
        this.matchOddsRepository = matchOddsRepository;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public MatchResponseDto createMatch(MatchDto matchDto) throws MatchAlreadyExistsException, MatchServiceException {

        try {
            if (this.matchRepository.findByTeamAAndTeamBAndMatchDateAndSport(matchDto.getTeamA(), matchDto.getTeamB(),
                    matchDto.getMatchDate(), matchDto.getSport()).isPresent()) {
                throw new MatchAlreadyExistsException("Creation failed. Match already exists!");
            }

            Match match = this.matchRepository.save(toMatchEntity(matchDto));

            saveOdds(match, matchDto.getMatchOdds());

            return MatchResponseDto.from(match);

        } catch (MatchAlreadyExistsException e) {
            throw new MatchAlreadyExistsException(e.getMessage());
        } catch (Exception e) {
            log.error("Failed to create match: {}", e.getMessage());
            throw new MatchServiceException();
        }
    }

    @Override
    public List<MatchDto> findAll() {
        return List.of();
    }

    @Override
    public MatchDto findMatchById(long id) throws MatchServiceException, MatchNotFoundException {
        try {
            Match match = this.matchRepository.findById(id)
                    .orElseThrow(() -> new MatchNotFoundException("Match not found with id: " + id));
            return toMatchDto(match);
        } catch (MatchNotFoundException e) {
            throw new MatchNotFoundException(e.getMessage());
        } catch (Exception e) {
            log.error("Failed to find match: {}", e.getMessage());
            throw new MatchServiceException();
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteMatch(long id) throws MatchNotFoundException, MatchServiceException {
        try {
            Match match = this.matchRepository.findById(id)
                    .orElseThrow(() -> new MatchNotFoundException("Match not found with id: " + id));

            this.matchRepository.delete(match);
            return true;
        } catch (MatchNotFoundException e) {
            throw new MatchNotFoundException(e.getMessage());
        } catch (Exception e) {
            log.error("Failed to delete match: {}", e.getMessage());
            throw new MatchServiceException();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MatchDto updateMatch(MatchUpdateRequestDto newMatch) throws MatchNotFoundException, MatchServiceException {
        try {
            Match match = this.matchRepository.findById(newMatch.getId())
                    .orElseThrow(() -> new MatchNotFoundException("Match not found with id: " + newMatch.getId()));

            this.updateEntity(match, newMatch);
            this.validate(match);

            return toMatchDto(this.matchRepository.save(match));

        } catch (MatchNotFoundException e) {
            throw new MatchNotFoundException(e.getMessage());
        } catch (Exception e) {
            log.error("Failed to update match: {}", e.getMessage());
            throw new MatchServiceException();
        }
    }

    private Match toMatchEntity(MatchDto requestDto) {
        return new Match.Builder()
                .withDescription(requestDto.getDescription())
                .withMatchDate(requestDto.getMatchDate())
                .withMatchTime(requestDto.getMatchTime())
                .withTeamA(requestDto.getTeamA())
                .withTeamB(requestDto.getTeamB())
                .withSport(requestDto.getSport())
//                .withMatchOdds(MatchOdds.Builder) // todo
                .build();
    }

    private MatchDto toMatchDto(Match match) {
        return new MatchDto.Builder()
                .withId(match.getId())
                .withDescription(match.getDescription())
                .withMatchDate(match.getMatchDate())
                .withMatchTime(match.getMatchTime())
                .withTeamA(match.getTeamA())
                .withTeamB(match.getTeamB())
                .withSport(match.getSport())
                .withMatchOdds(toMatchOddsDto(match.getMatchOdds()))
                .build();

    }

    private List<MatchOdds> toMatchOddsEntity(List<MatchOddsDto> odds) {
        return odds.stream()
                .map(odd -> new MatchOdds.Builder()
                        .withSpecifier(odd.getSpecifier())
                        .withOdd(odd.getOdd())
                        .withMatch(odd.getMatch())
                        .build()
                )
                .collect(Collectors.toList());
    }
    private List<MatchOddsDto> toMatchOddsDto(List<MatchOdds> odds) {
        return odds.stream()
                .map(odd -> new MatchOddsDto.Builder()
                        .withSpecifier(odd.getSpecifier())
                        .withOdd(odd.getOdd())
                        .withMatch(odd.getMatch())
                        .build()
                )
                .collect(Collectors.toList());
    }

    private void saveOdds(Match match, List<MatchOddsDto> matchOdds) {
        for (MatchOddsDto odd : matchOdds) {
            if (odd != null) {
                MatchOdds matchOdd = new MatchOdds.Builder()
                        .withSpecifier(odd.getSpecifier())
                        .withOdd(odd.getOdd())
//                        .withMatch(odd.getMatch())
                        .build();
                matchOdd.setMatch(match);
                matchOddsRepository.save(matchOdd);
            }
        }
    }

    private void updateEntity(Match match, MatchUpdateRequestDto newMatch) {
        if (!StringUtils.isEmpty(newMatch.getDescription())) {
            match.setDescription(newMatch.getDescription());
        }
        if (newMatch.getMatchDate() != null) {
            match.setMatchDate(newMatch.getMatchDate());
        }
        if (newMatch.getMatchTime() != null) {
            match.setMatchTime(newMatch.getMatchTime());
        }
        if (!StringUtils.isEmpty(newMatch.getTeamA())) {
            match.setTeamA(newMatch.getTeamA());
        }
        if (!StringUtils.isEmpty(newMatch.getTeamB())) {
            match.setTeamB(newMatch.getTeamB());
        }
        if (newMatch.getSport() != null) {
            match.setSport(newMatch.getSport());
        }
        if (newMatch.getMatchOdds() != null) {
            match.setMatchOdds(toMatchOddsEntity(newMatch.getMatchOdds()));
        }

    }

    @Override
    public Validator getValidator() {
        return validator;
    }
}
