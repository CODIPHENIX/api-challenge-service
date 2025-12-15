package com.apichallengeservice.services.impl;

import com.apichallengeservice.dtos.user.UserDTO;
import com.apichallengeservice.dtos.user.UserProfilDTO;
import com.apichallengeservice.services.UserServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;



@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServiceClient {

    //private static final String BASE_URL = "http://localhost:8081";
    private static final String BASE_URL = "http://192.168.1.138:8081";
    private final WebClient webClient;
    @Override
    public Optional<UserDTO> getUserById(Long creatorId) {
        UserDTO user = webClient.get()
                .uri(BASE_URL + "/api/users/" + creatorId)
                .retrieve()
                .bodyToMono(UserDTO.class)
                .block();

        return Optional.ofNullable(user);
    }

    @Override
    public Mono<UserProfilDTO> getUserProfil(Long creatorId) {
        return webClient.get()
                .uri(BASE_URL + "/api/users/{creatorId}/profil", creatorId)
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals, response ->
                   Mono.empty()// ne déclenche pas d'exception
                )
                .bodyToMono(UserProfilDTO.class);
    }
}
