package com.apichallengeservice.service;

import com.apichallengeservice.dto.RewardCreateDTO;
import com.apichallengeservice.dto.RewardDTO;
import com.apichallengeservice.dto.RewardUpdateDTO;
import com.apichallengeservice.entity.Challenge;
import com.apichallengeservice.entity.ChallengeReward;
import com.apichallengeservice.mapper.RewardMapper;
import com.apichallengeservice.exception.BadRequestException;
import com.apichallengeservice.exception.ResourceNotFoundException;
import com.apichallengeservice.repository.ChallengeRepository;
import com.apichallengeservice.repository.ChallengeRewardRepository;
import org.springframework.stereotype.Service;

@Service
public class ChallengeRewardService {

    private final ChallengeRewardRepository repo;
    private final ChallengeRepository challengeRepo;
    private final UserServiceClient userServiceClient;

    public ChallengeRewardService(ChallengeRewardRepository repo,
                                  ChallengeRepository challengeRepo,
                                  UserServiceClient userServiceClient) {
        this.repo = repo;
        this.challengeRepo = challengeRepo;
        this.userServiceClient = userServiceClient;
    }

    public RewardDTO setReward(Long challengeId, RewardCreateDTO dto) {

        Challenge challenge = challengeRepo.findById(challengeId)
                .orElseThrow(() -> new ResourceNotFoundException("Challenge not found"));

        // Vérification badge (si fourni)
        if (dto.getBadgeId() != null
                && userServiceClient.getUserById(dto.getBadgeId()).isEmpty()) {
            throw new BadRequestException("Badge does not exist in User Service");
        }

        ChallengeReward reward = RewardMapper.fromCreateDTO(dto);
        reward.setChallenge(challenge);

        ChallengeReward saved = repo.save(reward);

        return RewardMapper.toDTO(saved);
    }

    public RewardDTO getReward(Long challengeId) {

        ChallengeReward reward = repo.findByChallengeId(challengeId)
                .orElseThrow(() -> new ResourceNotFoundException("Reward not found"));

        return RewardMapper.toDTO(reward);
    }

    public RewardDTO updateReward(Long id, RewardUpdateDTO dto) {
        ChallengeReward reward = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reward not found with id : " + id)
                );
        // Vérifier badge si jamis il est fourni
        if (dto.getBadgeId() != null &&
                userServiceClient.getUserById(dto.getBadgeId()).isEmpty()) {
            throw new BadRequestException("Badge does not exist in User Service with id : " + dto.getBadgeId());
        }
        RewardMapper.updateEntity(reward, dto);
        return RewardMapper.toDTO(repo.save(reward));
    }
}