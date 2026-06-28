package com.company.employee.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfig {


    @Bean
    public OpenAPI employeeAPI(){


        return new OpenAPI()

                .info(
                    new Info()
                    .title("Employee Management API")
                    .description(
                       "AI Ready Employee Management System API"
                    )
                    .version("1.0")
                );

    }

}