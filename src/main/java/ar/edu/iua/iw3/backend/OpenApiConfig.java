package ar.edu.iua.iw3.backend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("Backend - Ing Web 3")
                        .version("v1.0")
                        .description("API Backend - Ing Web 3")
                        .contact(new Contact()
                                .name("iua")
                                .url("https://github.com/agussanguesa32/iw3_mariano")
                        )
                        .termsOfService("TOC")
                        .license(new License().name("License").url("#"))
                );
    }
}