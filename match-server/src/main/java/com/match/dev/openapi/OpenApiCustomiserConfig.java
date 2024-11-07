package com.match.dev.openapi;

import com.match.dev.openapi.constants.MatchResponses;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class OpenApiCustomiserConfig {

    @Bean
    public OpenApiCustomizer openApiCustomizerInfo(){
        return openAPI -> {
            Info info = new Info();
            info.setTitle("Match Application Swagger");
            info.setDescription(MatchResponses.APP_DESCRIPTION);
            info.setVersion("1");
            openAPI.setInfo(info);
        };
    }
}