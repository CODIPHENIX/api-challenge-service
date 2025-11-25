package com.apichallengeservice.mapper;

import com.apichallengeservice.dto.RewardDTO;
import com.apichallengeservice.dto.RewardCreateDTO;
import com.apichallengeservice.dto.RewardUpdateDTO;
import com.apichallengeservice.entity.ChallengeReward;

public class RewardMapper {

    public static RewardDTO toDTO(ChallengeReward reward) {
        RewardDTO dto = new RewardDTO();
        dto.setId(reward.getId());
        dto.setPoints(reward.getPoints());
        dto.setBadgeId(reward.getBadgeId());
        dto.setDescription(reward.getDescription());
        return dto;
    }

    public static ChallengeReward fromCreateDTO(RewardCreateDTO dto) {
        ChallengeReward reward = new ChallengeReward();
        reward.setPoints(dto.getPoints());
        reward.setBadgeId(dto.getBadgeId());
        reward.setDescription(dto.getDescription());
        return reward;
    }

    public static ChallengeReward updateEntity(ChallengeReward reward, RewardUpdateDTO dto) {
        reward.setPoints(dto.getPoints());
        reward.setBadgeId(dto.getBadgeId());
        reward.setDescription(dto.getDescription());
        return reward;
    }
}