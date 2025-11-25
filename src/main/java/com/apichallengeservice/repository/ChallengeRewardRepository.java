package com.apichallengeservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apichallengeservice.entity.ChallengeReward;

public interface ChallengeRewardRepository extends JpaRepository<ChallengeReward, Long> {
    Optional<ChallengeReward> findByChallengeId(Long challengeId);
}
