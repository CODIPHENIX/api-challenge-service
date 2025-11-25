package com.apichallengeservice.mapper;

import com.apichallengeservice.dto.*;
import com.apichallengeservice.entity.*;
import com.apichallengeservice.dto.client.UserProfileDTO;

public class ChallengeMapper {

    public static ChallengeDTO toDTO(Challenge challenge,
            UserProfileDTO creatorProfile,
            java.util.List<ObjectiveDTO> objectives,
            java.util.List<RuleDTO> rules,
            RewardDTO reward) {

        ChallengeDTO dto = new ChallengeDTO();

        dto.setId(challenge.getId());
        dto.setTitle(challenge.getTitle());
        dto.setDescription(challenge.getDescription());
        dto.setCategory(challenge.getCategory().name());
        dto.setDifficulty(challenge.getDifficulty().name());
        dto.setCreatorUserId(challenge.getCreatorUserId());
        dto.setStartDate(challenge.getStartDate());
        dto.setEndDate(challenge.getEndDate());
        dto.setIsActive(challenge.getIsActive());
        dto.setCreatorProfile(creatorProfile);

        dto.setObjectives(objectives);
        dto.setRules(rules);
        dto.setReward(reward);

        return dto;
    }

    public static Challenge toEntityFromCreate(ChallengeCreateDTO dto) {
        Challenge challenge = new Challenge();

        challenge.setTitle(dto.getTitle());
        challenge.setDescription(dto.getDescription());
        challenge.setCategory(ChallengeCategory.valueOf(dto.getCategory()));
        challenge.setDifficulty(ChallengeDifficulty.valueOf(dto.getDifficulty()));
        challenge.setCreatorUserId(dto.getCreatorUserId());
        challenge.setStartDate(dto.getStartDate());
        challenge.setEndDate(dto.getEndDate());
        challenge.setIsActive(dto.getIsActive());

        return challenge;
    }

    public static void updateEntityFromDTO(ChallengeUpdateDTO dto, Challenge challenge) {
        challenge.setTitle(dto.getTitle());
        challenge.setDescription(dto.getDescription());
        challenge.setCategory(ChallengeCategory.valueOf(dto.getCategory()));
        challenge.setDifficulty(ChallengeDifficulty.valueOf(dto.getDifficulty()));
        challenge.setStartDate(dto.getStartDate());
        challenge.setEndDate(dto.getEndDate());
        challenge.setIsActive(dto.getIsActive());
    }
}