package com.apichallengeservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apichallengeservice.entity.ChallengeObjective;

public interface ChallengeObjectiveRepository extends JpaRepository<ChallengeObjective, Long> {
    List<ChallengeObjective> findByChallengeId(Long challengeId);
}
