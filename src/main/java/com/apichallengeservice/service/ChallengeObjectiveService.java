package com.apichallengeservice.service;

import com.apichallengeservice.entity.Challenge;
import com.apichallengeservice.entity.ChallengeObjective;
import com.apichallengeservice.repository.ChallengeObjectiveRepository;
import com.apichallengeservice.repository.ChallengeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeObjectiveService {
    private final ChallengeObjectiveRepository objectiveRepository;
    private final ChallengeRepository challengeRepository;

    public ChallengeObjectiveService(ChallengeObjectiveRepository objectiveRepository,
                                     ChallengeRepository challengeRepository) {
        this.objectiveRepository = objectiveRepository;
        this.challengeRepository = challengeRepository;
    }

    public ChallengeObjective addObjective(Long challengeId, ChallengeObjective objective) {
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new RuntimeException("Challenge not found"));

        objective.setChallenge(challenge);
        return objectiveRepository.save(objective);
    }

    public List<ChallengeObjective> getObjectives(Long challengeId) {
        return objectiveRepository.findByChallengeId(challengeId);
    }

    public ChallengeObjective updateObjective(Long id, ChallengeObjective updated) {
        ChallengeObjective obj = objectiveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Objective not found"));

        obj.setObjectiveType(updated.getObjectiveType());
        obj.setTargetValue(updated.getTargetValue());
        obj.setUnit(updated.getUnit());

        return objectiveRepository.save(obj);
    }

    public void deleteObjective(Long id) {
        objectiveRepository.deleteById(id);
    }

    
}
