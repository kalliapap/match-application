package com.match.dev.controller;

import com.match.dev.dto.*;
import com.match.dev.exception.MatchAlreadyExistsException;
import com.match.dev.exception.MatchNotFoundException;
import com.match.dev.exception.MatchOddValidationFailedException;
import com.match.dev.exception.MatchServiceException;
import com.match.dev.openapi.controllers.MatchControllerOpenApi;
import com.match.dev.service.MatchService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/match")
public class MatchController implements MatchControllerOpenApi {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDto<MatchResponseDto>> createMatch(@Valid @
            RequestBody MatchDto request) throws MatchServiceException, MatchAlreadyExistsException, MatchOddValidationFailedException {
        MatchResponseDto response = matchService.createMatch(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), response));
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDto<List<MatchDto>>> getAllMatches() throws MatchServiceException, MatchNotFoundException {
        List<MatchDto> response = matchService.findAllMatches();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), response));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDto<MatchDto>> getMatchById(@PathVariable long id) throws MatchServiceException, MatchNotFoundException {
        MatchDto response = matchService.findMatchById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), response));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDto<?>> updateMatch(@Valid @RequestBody MatchUpdateRequestDto request) throws MatchServiceException, MatchNotFoundException, MatchOddValidationFailedException {
        matchService.updateMatch(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "Match updated successfully"));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDto<?>> deleteMatch(@PathVariable long id) throws MatchServiceException, MatchNotFoundException {
        matchService.deleteMatch(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "Match deleted successfully"));
    }

}