package com.apichallengeservice.services;

import com.apichallengeservice.dtos.user.UserDTO;
import com.apichallengeservice.dtos.user.UserProfilDTO;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface UserServiceClient {

    Optional<UserDTO> getUserById(Long creatorId);
    Mono<UserProfilDTO> getUserProfil(Long creatorId);


}
