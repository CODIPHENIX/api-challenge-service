package com.apichallengeservice.service;

import com.apichallengeservice.dto.client.UserProfileDTO;
import com.apichallengeservice.entity.Challenge;
import com.apichallengeservice.entity.ChallengeCategory;
import com.apichallengeservice.entity.ChallengeDifficulty;
import com.apichallengeservice.repository.ChallengeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChallengeService {
    private final ChallengeRepository challengeRepository;
    private final UserServiceClient userServiceClient;

    public ChallengeService(ChallengeRepository challengeRepository, UserServiceClient userServiceClient) {
        this.challengeRepository = challengeRepository;
        this.userServiceClient = userServiceClient;
    }

    public Challenge createChallenge(Challenge challenge) {

        boolean userExists = userServiceClient
                .getUserById(challenge.getCreatorUserId())
                .isPresent();

        if (!userExists) {
            throw new RuntimeException("Creator user does not exist");
        }

        return challengeRepository.save(challenge);
    }

    public Challenge getChallengeById(Long id) {
        return challengeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Challenge not found"));
    }

    public Page<Challenge> getAllChallenges(Pageable pageable) {
        return challengeRepository.findAll(pageable);
    }

    public Challenge updateChallenge(Long id, Challenge updated) {
        Challenge challenge = getChallengeById(id);

        challenge.setTitle(updated.getTitle());
        challenge.setDescription(updated.getDescription());
        challenge.setCategory(updated.getCategory());
        challenge.setDifficulty(updated.getDifficulty());
        challenge.setStartDate(updated.getStartDate());
        challenge.setEndDate(updated.getEndDate());
        challenge.setIsActive(updated.getIsActive());

        return challengeRepository.save(challenge);
    }

    public void deleteChallenge(Long id) {
        Challenge challenge = getChallengeById(id);
        challengeRepository.delete(challenge);
    }

    public Page<Challenge> getActive(Pageable pageable) {
        return challengeRepository.findByIsActiveTrue(pageable);
    }

    public Page<Challenge> getByCategory(String category, Pageable pageable) {
        return challengeRepository.findByCategory(
                Enum.valueOf(ChallengeCategory.class, category), pageable);
    }

    public Page<Challenge> getByDifficulty(String difficulty, Pageable pageable) {
        return challengeRepository.findByDifficulty(
                Enum.valueOf(ChallengeDifficulty.class, difficulty), pageable);
    }

    public Page<Challenge> getByCreator(Long creatorId, Pageable pageable) {
        return challengeRepository.findByCreatorUserId(creatorId, pageable);
    }

    public Optional<UserProfileDTO> getCreatorProfile(Long userId) {
        return userServiceClient.getUserProfileById(userId);
    }

}
