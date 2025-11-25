package com.apichallengeservice.service;

import com.apichallengeservice.dto.client.UserDTO;
import com.apichallengeservice.dto.client.UserProfileDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class UserServiceClient {
    private final RestTemplate restTemplate;
    private final String baseUrl;

    public UserServiceClient(RestTemplate restTemplate, @Value("${user.service.url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    public Optional<UserDTO> getUserById(Long id) {
        try {
            UserDTO user = restTemplate.getForObject(baseUrl + "/api/users/" + id, UserDTO.class);
            return Optional.ofNullable(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<UserProfileDTO> getUserProfileById(Long id) {
        try {
            UserProfileDTO userProfile = restTemplate.getForObject(baseUrl + "/api/users/" + id + "/profile", UserProfileDTO.class);
            return Optional.ofNullable(userProfile);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
