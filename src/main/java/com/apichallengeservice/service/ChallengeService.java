package com.apichallengeservice.service;

import com.apichallengeservice.dto.*;
import com.apichallengeservice.dto.client.UserProfileDTO;
import com.apichallengeservice.entity.Challenge;
import com.apichallengeservice.exception.BadRequestException;
import com.apichallengeservice.exception.ResourceNotFoundException;
import com.apichallengeservice.mapper.*;
import com.apichallengeservice.repository.ChallengeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class ChallengeService {
    private final ChallengeRepository challengeRepository;
    private final UserServiceClient userServiceClient;

    private final ChallengeObjectiveService objectiveService;
    private final ChallengeRuleService ruleService;
    private final ChallengeRewardService rewardService;

    public ChallengeService(ChallengeRepository challengeRepository, UserServiceClient userServiceClient,
            ChallengeObjectiveService objectiveService, ChallengeRuleService ruleService,
            ChallengeRewardService rewardService) {
        this.challengeRepository = challengeRepository;
        this.userServiceClient = userServiceClient;
        this.objectiveService = objectiveService;
        this.ruleService = ruleService;
        this.rewardService = rewardService;
    }

    public ChallengeDTO createChallenge(ChallengeCreateDTO dto) {

        boolean exists = userServiceClient
                .getUserById(dto.getCreatorUserId())
                .isPresent();

        if (!exists) {
            throw new BadRequestException("Creator user does not exist");
        }

        Challenge challenge = ChallengeMapper.toEntityFromCreate(dto);

        Challenge saved = challengeRepository.save(challenge);

        return toCompleteDTO(saved);
    }

    public ChallengeDTO getChallengeById(Long id) {
        Challenge challenge = challengeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Challenge not found"));

        return toCompleteDTO(challenge);
    }

    public Page<ChallengeDTO> getAllChallenges(Pageable pageable) {
        return challengeRepository.findAll(pageable)
                .map(this::toCompleteDTO);
    }

    public ChallengeDTO updateChallenge(Long id, ChallengeUpdateDTO dto) {

        Challenge existing = challengeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Challenge not found"));

        ChallengeMapper.updateEntityFromDTO(dto, existing);

        Challenge saved = challengeRepository.save(existing);

        return toCompleteDTO(saved);
    }

    public void deleteChallenge(Long id) {
        Challenge challenge = challengeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Challenge not found with id : " + id));

        challengeRepository.delete(challenge);
    }

    public Page<ChallengeDTO> getActive(Pageable pageable) {
        return challengeRepository.findByIsActiveTrue(pageable)
                .map(this::toCompleteDTO);
    }

    public Page<ChallengeDTO> getByCategory(String category, Pageable pageable) {
        return challengeRepository.findByCategory(
                Enum.valueOf(com.apichallengeservice.entity.ChallengeCategory.class, category),
                pageable).map(this::toCompleteDTO);
    }

    public Page<ChallengeDTO> getByDifficulty(String diff, Pageable pageable) {
        return challengeRepository.findByDifficulty(
                Enum.valueOf(com.apichallengeservice.entity.ChallengeDifficulty.class, diff),
                pageable).map(this::toCompleteDTO);
    }

    public Page<ChallengeDTO> getByCreator(Long userId, Pageable pageable) {
        return challengeRepository.findByCreatorUserId(userId, pageable)
                .map(this::toCompleteDTO);
    }

    private ChallengeDTO toCompleteDTO(Challenge challenge) {

        UserProfileDTO profile = userServiceClient
                .getUserProfileById(challenge.getCreatorUserId())
                .orElse(null);

        List<ObjectiveDTO> objectives = objectiveService.getObjectives(challenge.getId());

        List<RuleDTO> rules = ruleService.getRules(challenge.getId());

        RewardDTO reward = null;
        try {
            reward = rewardService.getReward(challenge.getId());
        } catch (Exception ignored) {
        }

        return ChallengeMapper.toDTO(
                challenge,
                profile,
                objectives,
                rules,
                reward);
    }

    public Optional<UserProfileDTO> getCreatorProfile(Long userId) {
        return userServiceClient.getUserProfileById(userId);
    }

}
