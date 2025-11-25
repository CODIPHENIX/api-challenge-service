package com.apichallengeservice.service;

import org.springframework.stereotype.Service;

import com.apichallengeservice.entity.Challenge;
import com.apichallengeservice.entity.ChallengeReward;
import com.apichallengeservice.repository.ChallengeRewardRepository;
import com.apichallengeservice.repository.ChallengeRepository;



@Service
public class ChallengeRewardService {

    private final ChallengeRewardRepository rewardRepository;
    private final ChallengeRepository challengeRepository;
    private final UserServiceClient userServiceClient;

    public ChallengeRewardService(ChallengeRewardRepository rewardRepository,
                                  ChallengeRepository challengeRepository,
                                  UserServiceClient userServiceClient) {
        this.rewardRepository = rewardRepository;
        this.challengeRepository = challengeRepository;
        this.userServiceClient = userServiceClient;
    }

    public ChallengeReward setReward(Long challengeId, ChallengeReward reward) {

        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new RuntimeException("Challenge not found"));

        if (reward.getBadgeId() != null &&
            userServiceClient.getUserById(reward.getBadgeId()).isEmpty()) {
            throw new RuntimeException("Badge does not exist in User Service");
        }

        reward.setChallenge(challenge);
        return rewardRepository.save(reward);
    }

    public ChallengeReward getReward(Long challengeId) {
        return rewardRepository.findByChallengeId(challengeId)
                .orElseThrow(() -> new RuntimeException("Reward not found"));
    }

    public ChallengeReward updateReward(Long id, ChallengeReward updated) {
        ChallengeReward reward = rewardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reward not found"));

        reward.setPoints(updated.getPoints());
        reward.setBadgeId(updated.getBadgeId());
        reward.setDescription(updated.getDescription());

        return rewardRepository.save(reward);
    }
}
