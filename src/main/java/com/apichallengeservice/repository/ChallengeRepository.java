package com.apichallengeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apichallengeservice.model.Challenge;

public interface ChallengeRepository extends JpaRepository<Challenge, Long>{

}
