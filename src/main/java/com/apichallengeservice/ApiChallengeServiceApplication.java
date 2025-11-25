package com.apichallengeservice;

import com.apichallengeservice.config.UserServiceProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(UserServiceProperties.class)
public class ApiChallengeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiChallengeServiceApplication.class, args);
	}

}
