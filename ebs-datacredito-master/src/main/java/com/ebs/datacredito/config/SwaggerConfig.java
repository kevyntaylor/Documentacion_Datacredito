package com.ebs.datacredito.config;

import com.ebs.datacredito.util.SwaggerPageable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ebs.datacredito.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                //.securityContexts(Arrays.asList(securityContext()))
                //.securitySchemes(Arrays.asList(apiKey()))
                .directModelSubstitute(Pageable.class, SwaggerPageable.class);
    }


    private ApiInfo apiInfo() {
        return new ApiInfo(
                "EBS API REST Datacredito",
                "Api RESTFull for get credit history from Datacredito.",
                "v1.0.0",
                "https://www.ebssas.com",
                new Contact("Miller Mejia Alvarez", "https://www.ebssas.com", "miller.mejia98@gmail.com"),
                "License del API", "API license URL", Collections.emptyList());
    }

}
