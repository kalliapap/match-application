package com.match.dev.handler;

import com.match.dev.dto.ApiResponseDto;
import com.match.dev.dto.ApiResponseStatus;
import com.match.dev.exception.*;
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

    @ExceptionHandler(value = {MatchNotFoundException.class, MatchOddNotFoundException.class})
    public ResponseEntity<ApiResponseDto<?>> matchNotFoundExceptionHandler(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseDto<>(ApiResponseStatus.FAIL.name(), exception.getMessage()));
    }

    @ExceptionHandler(value = {MatchServiceException.class, MatchOddValidationFailedException.class})
    public ResponseEntity<ApiResponseDto<?>> matchServiceExceptionHandler(Exception exception) {
        return ResponseEntity.badRequest().body(new ApiResponseDto<>(ApiResponseStatus.FAIL.name(), exception.getMessage()));
    }
}
