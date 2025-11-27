package com.apichallengeservice.service;

import com.apichallengeservice.dto.ObjectiveCreateDTO;
import com.apichallengeservice.dto.ObjectiveDTO;
import com.apichallengeservice.dto.ObjectiveUpdateDTO;
import com.apichallengeservice.entity.Challenge;
import com.apichallengeservice.entity.ChallengeObjective;
import com.apichallengeservice.exception.ResourceNotFoundException;
import com.apichallengeservice.mapper.ObjectiveMapper;
import com.apichallengeservice.repository.ChallengeObjectiveRepository;
import com.apichallengeservice.repository.ChallengeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChallengeObjectiveService {

    private final ChallengeObjectiveRepository repo;
    private final ChallengeRepository challengeRepo;

    public ChallengeObjectiveService(ChallengeObjectiveRepository repo,
                                     ChallengeRepository challengeRepo) {
        this.repo = repo;
        this.challengeRepo = challengeRepo;
    }

    public ObjectiveDTO addObjective(Long challengeId, ObjectiveCreateDTO dto) {

        Challenge challenge = challengeRepo.findById(challengeId)
                .orElseThrow(() -> new ResourceNotFoundException("Challenge not found"));

        ChallengeObjective entity = ObjectiveMapper.fromCreateDTO(dto);
        entity.setChallenge(challenge);

        ChallengeObjective saved = repo.save(entity);

        return ObjectiveMapper.toDTO(saved);
    }

    public List<ObjectiveDTO> getObjectives(Long challengeId) {
        return repo.findByChallengeId(challengeId)
                .stream()
                .map(ObjectiveMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ObjectiveDTO updateObjective(Long id, ObjectiveUpdateDTO dto) {
        ChallengeObjective obj = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Objective not found"));

        ObjectiveMapper.toEntity(obj, dto);

        return ObjectiveMapper.toDTO(repo.save(obj));
    }

    public void deleteObjective(Long id) {
        boolean exists = repo.existsById(id);
        if (!exists) {
            throw new ResourceNotFoundException("Objective not found with id : " + id);
        }
        repo.deleteById(id);
    }
}