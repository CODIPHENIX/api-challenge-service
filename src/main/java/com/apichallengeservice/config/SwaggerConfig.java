package com.apichallengeservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${app.version:1.0.0}")
    private String appVersion;

    @Value("${app.name:Challenge Service RESTFull API}")
    private String appName;

    @Value("${app.description:API documentation for Challenge Service RESTFull API}")
    private String appDescription;

    @Value("${server.servlet.context-path:}")
    private String contextPath;

    @Value("${server.port}")
    private String serverPort;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(getApiInfo())
                .servers(getServers());
    }

    private Info getApiInfo() {
        return new Info()
                .title(appName)
                .description(appDescription)
                .version(appVersion)
                .license(getLicense());
    }

    private License getLicense() {
        return new License()
                .name("MIT License")
                .url("https://opensource.org/licenses/MIT");
    }

    private List<Server> getServers() {
        return List.of(
                new Server()
                     //   .url("http://localhost:" + serverPort + contextPath)
                        .url("http://localhost:" + serverPort + "/api")
                        .description("Development server")
        );
    }

}
