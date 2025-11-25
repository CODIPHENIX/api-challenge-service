package com.apichallengeservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apichallengeservice.entity.ChallengeRule;

public interface ChallengeRuleRepository extends JpaRepository<ChallengeRule, Long>{

    List<ChallengeRule> findByChallengeIdOrderByOrderIndexAsc(Long challengeId);
    
}
