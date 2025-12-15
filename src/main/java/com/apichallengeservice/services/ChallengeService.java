package com.apichallengeservice.services;

import com.apichallengeservice.dtos.challenge.RequestChallengeDTO;
import com.apichallengeservice.dtos.challenge.ResponseChallengeDTO;
import com.apichallengeservice.dtos.challenge.UpdateChallengeDTO;
import org.springframework.data.domain.Page;

public interface ChallengeService {

    ResponseChallengeDTO createChallenge(RequestChallengeDTO requestChallengeDTO);
    ResponseChallengeDTO findChallengeByID(Long id);
    Page<ResponseChallengeDTO> findAllChallenges(int page, int size);
    ResponseChallengeDTO updateChallenges(UpdateChallengeDTO updateChallengeDTO, Long idChallenge);
    void deleteChallenge(Long id);
}
