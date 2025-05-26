package org.example.ringlesubject.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Info info = new Info()
                .version("1.0")
                .title("Ringle Subject")
                .description("튜터링 수강 신청 API");
        return new OpenAPI()
                .info(info);
    }
}
