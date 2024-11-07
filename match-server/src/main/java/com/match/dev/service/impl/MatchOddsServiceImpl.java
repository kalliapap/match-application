package com.match.dev.service.impl;

import com.match.dev.dto.MatchOddDto;

import com.match.dev.dto.MatchOddRequestDto;
import com.match.dev.entity.Match;
import com.match.dev.entity.MatchOdds;
import com.match.dev.exception.MatchNotFoundException;
import com.match.dev.exception.MatchOddNotFoundException;
import com.match.dev.exception.MatchServiceException;
import com.match.dev.repository.MatchOddsRepository;
import com.match.dev.repository.MatchRepository;
import com.match.dev.service.MatchOddsService;
import com.match.dev.util.MatchUtils;
import jakarta.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchOddsServiceImpl implements MatchOddsService {

    private final Logger log = LoggerFactory.getLogger(MatchOddsServiceImpl.class);

    private final MatchRepository matchRepository;
    private final MatchOddsRepository matchOddsRepository;

    @Autowired
    private Validator validator;

    public MatchOddsServiceImpl(MatchOddsRepository matchOddsRepository, MatchRepository matchRepository) {
        this.matchOddsRepository = matchOddsRepository;
        this.matchRepository = matchRepository;
    }

    @Override
    @Transactional
    public MatchOddDto createOdd(MatchOddRequestDto matchOddRequestDto) throws MatchNotFoundException, MatchServiceException {
        try {
            Match match = this.matchRepository.findById(matchOddRequestDto.getMatchId())
                    .orElseThrow(() -> new MatchNotFoundException("Cannot add odd to a non-existing match!"));

            this.matchOddsRepository.save(MatchUtils.toMatchOddsEntity(matchOddRequestDto, match));

        } catch (MatchNotFoundException e) {
            throw new MatchNotFoundException(e.getMessage());
        } catch (Exception e) {
            log.error("Failed to add match odd: {}", e.getMessage());
            throw new MatchServiceException();
        }
        return null;
    }

    @Override
    public List<MatchOddDto> findAll() throws MatchServiceException {
        try {

            List<MatchOdds> matchOdds = matchOddsRepository.findAll();

            return matchOdds.stream()
                    .map(matchOdd -> new MatchOddDto.Builder()
                            .withOdd(matchOdd.getOdd())
                            .withSpecifier(matchOdd.getSpecifier())
                            .build()
                    )
                    .collect(Collectors.toList());

        } catch (Exception e) {
            log.error("Failed to fetch all odds: {}", e.getMessage());
            throw new MatchServiceException();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteOdd(long id) throws MatchServiceException, MatchOddNotFoundException {
        try {
            MatchOdds matchOdds = this.matchOddsRepository.findById(id)
                    .orElseThrow(() -> new MatchOddNotFoundException("Match odd not found with id: " + id));

            this.matchOddsRepository.delete(matchOdds);
        } catch (MatchOddNotFoundException e) {
            throw new MatchOddNotFoundException(e.getMessage());
        } catch (Exception e) {
            log.error("Failed to delete odd with id: {},{}", id, e.getMessage());
            throw new MatchServiceException();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOdd(MatchOddDto newMatchOdds) throws MatchOddNotFoundException, MatchServiceException {
        try {
            MatchOdds matchOdds = this.matchOddsRepository.findById(newMatchOdds.getId())
                    .orElseThrow(() -> new MatchOddNotFoundException("Match odd not found with id: " + newMatchOdds.getId()));

            this.updateEntity(matchOdds, newMatchOdds);

            MatchUtils.toMatchOddsDto(this.matchOddsRepository.save(matchOdds));

        } catch (MatchOddNotFoundException e) {
            throw new MatchOddNotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new MatchServiceException();
        }
    }

    private void updateEntity(MatchOdds matchOdds, MatchOddDto newMatchOdds) {
        if (!StringUtils.isEmpty(newMatchOdds.getSpecifier())) {
            matchOdds.setSpecifier(newMatchOdds.getSpecifier());
        }
        if (newMatchOdds.getOdd() != 0) {
            matchOdds.setOdd(newMatchOdds.getOdd());
        }
    }
}
