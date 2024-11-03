package com.match.dev.handler;

import com.match.dev.dto.ApiResponseDto;
import com.match.dev.dto.ApiResponseStatus;
import com.match.dev.exception.MatchAlreadyExistsException;
import com.match.dev.exception.MatchNotFoundException;
import com.match.dev.exception.MatchServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MatchServiceExceptionHandler {

    @ExceptionHandler(value = MatchAlreadyExistsException.class)
    public ResponseEntity<ApiResponseDto<?>> matchAlreadyExistsExceptionHandler(MatchAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseDto<>(ApiResponseStatus.FAIL.name(), exception.getMessage()));
    }

    @ExceptionHandler(value = MatchNotFoundException.class)
    public ResponseEntity<ApiResponseDto<?>> matchNotFoundExceptionHandler(MatchNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseDto<>(ApiResponseStatus.FAIL.name(), exception.getMessage()));
    }

    @ExceptionHandler(value = MatchServiceException.class)
    public ResponseEntity<ApiResponseDto<?>> matchServiceExceptionHandler(MatchServiceException exception) {
        return ResponseEntity.badRequest().body(new ApiResponseDto<>(ApiResponseStatus.FAIL.name(), exception.getMessage()));
    }
}
