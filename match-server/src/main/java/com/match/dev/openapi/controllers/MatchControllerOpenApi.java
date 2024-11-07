package com.match.dev.openapi.controllers;

import com.match.dev.dto.ApiResponseDto;
import com.match.dev.dto.MatchDto;
import com.match.dev.dto.MatchResponseDto;
import com.match.dev.exception.MatchAlreadyExistsException;
import com.match.dev.exception.MatchOddValidationFailedException;
import com.match.dev.exception.MatchServiceException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import static com.match.dev.openapi.constants.MatchResponses.*;


public interface MatchControllerOpenApi {


    @Operation(operationId = "hello", summary = "Api to create a new match", description = "Use to create a new match"
    )
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Match created successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MatchResponseDto.class), // success object with 200
                            examples = {
                                    @ExampleObject(name = "Match created", value = MATCH_SUCCESS_RESPONSE)
                            }
                        )
                    }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MatchResponseDto.class), // success object with 200
                            examples = {
                                    @ExampleObject(name = "Match creation failed", value = MATCH_RESPONSE_ERROR)
                            }
                        )
                    }),
            @ApiResponse(responseCode = "409", description = "Conflict",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MatchResponseDto.class), // success object with 200
                            examples = {
                                    @ExampleObject(name = "Match creation failed", value = MATCH_ALREADY_EXISTS_RESPONSE_ERROR)
                            }
                        )
                    }),
             }
    )
    ResponseEntity<ApiResponseDto<MatchResponseDto>> createMatch(@Valid @RequestBody MatchDto request)  throws MatchServiceException, MatchAlreadyExistsException, MatchOddValidationFailedException;
}