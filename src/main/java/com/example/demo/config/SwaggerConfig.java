package com.example.demo.config;

import io.swagger.models.auth.In;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    // <editor-fold defaultstate="collapsed" desc="Bean Definition for Dummy">

    /**
     * The Docket definition.
     *
     * @return Docket
     */
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .groupName("Demo Service")
            .forCodeGeneration(true)
            .genericModelSubstitutes(ResponseEntity.class)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.example.demo.web"))
            .paths(PathSelectors.any())
            .build();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Generate Api Info">

    /**
     * Generate Api Info.
     *
     * @return Swagger API Info
     */
    ApiInfo apiInfo(){
        return new ApiInfoBuilder()
            .title("Demo API")
            .description("API definition for the demo service")
            .version("0.0.1")
            .build();
    }
    // </editor-fold>
}
