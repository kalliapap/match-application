package com.match.dev.controller;

import com.match.dev.dto.ApiResponseDto;
import com.match.dev.dto.ApiResponseStatus;
import com.match.dev.dto.MatchOddDto;
import com.match.dev.dto.MatchOddRequestDto;
import com.match.dev.exception.MatchNotFoundException;
import com.match.dev.exception.MatchOddNotFoundException;
import com.match.dev.exception.MatchServiceException;
import com.match.dev.service.MatchOddsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/match-odds")
public class MatchOddsController {


    private final MatchOddsService matchOddsService;

    public MatchOddsController(MatchOddsService matchOddsService) {
        this.matchOddsService = matchOddsService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDto<MatchOddDto>> createMatchOdds(@Valid @RequestBody MatchOddRequestDto request) throws MatchServiceException, MatchNotFoundException {
        MatchOddDto response = matchOddsService.createOdd(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), response));
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDto<List<MatchOddDto>>> getAllMatchOdds() throws MatchServiceException {
        List<MatchOddDto> response = matchOddsService.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), response));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDto<?>> updateMatchOdds(@Valid @RequestBody MatchOddDto request) throws MatchOddNotFoundException, MatchServiceException {
        try {
            matchOddsService.updateOdd(request);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "Match odd updated successfully!"));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDto<?>> deleteMatchOdds(@PathVariable Long id) throws MatchOddNotFoundException, MatchServiceException {

        matchOddsService.deleteOdd(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "Match odd deleted successfully!"));

    }
}

