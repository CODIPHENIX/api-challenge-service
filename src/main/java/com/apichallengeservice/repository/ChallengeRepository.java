package com.apichallengeservice.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.apichallengeservice.entity.Challenge;
import com.apichallengeservice.entity.ChallengeCategory;
import com.apichallengeservice.entity.ChallengeDifficulty;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    Page<Challenge> findByIsActiveTrue(Pageable pageable);

    Page<Challenge> findByCategory(ChallengeCategory category, Pageable pageable);

    Page<Challenge> findByDifficulty(ChallengeDifficulty difficulty, Pageable pageable);

    Page<Challenge> findByCreatorUserId(Long creatorUserId, Pageable pageable);
}
