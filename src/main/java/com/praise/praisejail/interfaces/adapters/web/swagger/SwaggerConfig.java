package com.praise.praisejail.interfaces.adapters.web.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info=@Info(title="Praise Jail API",
        description="Praise Jail API v1.0",
        version="v1")
)
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi swaggerApi() {
        String [] paths = {"/*", "/**", "/api/**"};

        return GroupedOpenApi.builder()
            .group("Praise Jail API v1.0")
            .pathsToMatch(paths)
            .build();
    }
}
