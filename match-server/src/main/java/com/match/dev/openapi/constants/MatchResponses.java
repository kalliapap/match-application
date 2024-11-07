package com.match.dev.openapi.constants;

import org.springframework.stereotype.Component;

@Component
public class MatchResponses {

    public static final String APP_DESCRIPTION = """
            # General
            
            * **Usage**
            * This is the APIs to create a match and match odds
            * The app allows match creation, update, deletion and selection of all or one match
            """;

    public static final String MATCH_SUCCESS_RESPONSE = """
            {
                "status": "SUCCESS",
                "response": {
                    "matchId": 2
                }
            }
            """;

    public static final String MATCH_ALREADY_EXISTS_RESPONSE_ERROR = """
            {
            "status": "FAIL",
            "response": "Creation failed. Match already exists!"
            }
            """;
    public static final String MATCH_RESPONSE_ERROR = """
            {
            "status": "FAIL",
            "response": "Something went wrong. Please try again later!"
            }
            """;
}