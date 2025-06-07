package com.productos.duoc.cl.productos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

   @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "apiKeyAuth";

        // Agrega el esquema de seguridad
        OpenAPI openAPI = new OpenAPI()
            .info(new Info().title("API Productos de EcoMarket").version("1.0"))
            .components(new Components()
                .addSecuritySchemes(securitySchemeName,
                    new SecurityScheme()
                        .name("x-api-key") // <-- nombre del header que tu filtro espera
                        .type(SecurityScheme.Type.APIKEY)
                        .in(SecurityScheme.In.HEADER)))
            .addSecurityItem(new SecurityRequirement().addList(securitySchemeName));

        return openAPI;
    }
}