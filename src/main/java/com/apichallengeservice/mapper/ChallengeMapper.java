package com.apichallengeservice.mapper;

import com.apichallengeservice.dtos.challenge.RequestChallengeDTO;
import com.apichallengeservice.dtos.challenge.ResponseChallengeDTO;
import com.apichallengeservice.dtos.user.UserProfilDTO;
import com.apichallengeservice.model.Challenge;

public class ChallengeMapper {
    private ChallengeMapper(){}
    public static Challenge toEntity(RequestChallengeDTO requestChallengeDTO){

       return Challenge.builder()
                .title(requestChallengeDTO.getTitle())
                .description(requestChallengeDTO.getDescription())
                .category(requestChallengeDTO.getCategory())
                .difficulty(requestChallengeDTO.getDifficulty())
                .creatorUserId(requestChallengeDTO.getCreatorUserId())
                .startDate(requestChallengeDTO.getStartDate())
                .endDate(requestChallengeDTO.getEndDate())
                .build();

    }
    public static ResponseChallengeDTO toDTO(Challenge challenge) {

        return ResponseChallengeDTO.builder()
                .id(challenge.getId())
                .title(challenge.getTitle())
                .description(challenge.getDescription())
                .category(challenge.getCategory())
                .difficulty(challenge.getDifficulty())
                .creatorUserId(challenge.getCreatorUserId())
                .startDate(challenge.getStartDate())
                .endDate(challenge.getEndDate())
                .build();
    }
    public static ResponseChallengeDTO toDTO(Challenge challenge, UserProfilDTO userProfilDTO) {
        if (userProfilDTO == null || challenge == null ){
            return null;
        }

        ResponseChallengeDTO dto = toDTO(challenge);

        dto.setCreatorName(userProfilDTO.getCreatorName());
        dto.setCreatorLevel(userProfilDTO.getCreatorLevel());

        return dto;
    }





}
