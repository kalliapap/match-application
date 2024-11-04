package com.match.dev.controller;

import com.match.dev.dto.*;
import com.match.dev.exception.MatchAlreadyExistsException;
import com.match.dev.exception.MatchNotFoundException;
import com.match.dev.exception.MatchServiceException;
import com.match.dev.service.MatchService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/match")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDto<MatchResponseDto>> createMatch(@Valid @RequestBody MatchDto request) throws MatchServiceException, MatchAlreadyExistsException {
        MatchResponseDto response = matchService.createMatch(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), response));
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<ApiResponseDto<MatchDto>> getMatchById(@PathVariable long id) throws MatchServiceException, MatchNotFoundException {
        MatchDto response = matchService.findMatchById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), response));
    }

    @PutMapping
    public ResponseEntity<ApiResponseDto<?>> updateMatch(@Valid @RequestBody MatchUpdateRequestDto request) throws MatchServiceException, MatchNotFoundException {
        matchService.updateMatch(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "Match updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<?>> deleteMatch(@PathVariable long id) throws MatchServiceException, MatchNotFoundException {
        this.matchService.deleteMatch(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "Match deleted successfully"));
    }

}