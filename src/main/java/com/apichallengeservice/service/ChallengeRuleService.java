package com.apichallengeservice.service;

import com.apichallengeservice.dto.RuleCreateDTO;
import com.apichallengeservice.dto.RuleDTO;
import com.apichallengeservice.dto.RuleUpdateDTO;
import com.apichallengeservice.entity.Challenge;
import com.apichallengeservice.entity.ChallengeRule;
import com.apichallengeservice.mapper.RuleMapper;
import com.apichallengeservice.exception.ResourceNotFoundException;
import com.apichallengeservice.repository.ChallengeRepository;
import com.apichallengeservice.repository.ChallengeRuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChallengeRuleService {

    private final ChallengeRuleRepository repo;
    private final ChallengeRepository challengeRepo;

    public ChallengeRuleService(ChallengeRuleRepository repo, ChallengeRepository challengeRepo) {
        this.repo = repo;
        this.challengeRepo = challengeRepo;
    }

    public RuleDTO addRule(Long challengeId, RuleCreateDTO dto) {

        Challenge challenge = challengeRepo.findById(challengeId)
                .orElseThrow(() -> new ResourceNotFoundException("Challenge not found"));

        ChallengeRule rule = RuleMapper.fromCreateDTO(dto);
        rule.setChallenge(challenge);

        ChallengeRule saved = repo.save(rule);

        return RuleMapper.toDTO(saved);
    }

    public List<RuleDTO> getRules(Long challengeId) {
        return repo.findByChallengeIdOrderByOrderIndexAsc(challengeId)
                .stream()
                .map(RuleMapper::toDTO)
                .collect(Collectors.toList());
    }

    public RuleDTO updateRule(Long id, RuleUpdateDTO dto) {
        ChallengeRule rule = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));

        RuleMapper.updateEntity(rule, dto);

        return RuleMapper.toDTO(repo.save(rule));
    }

    public void deleteRule(Long id) {
        boolean exists = repo.existsById(id);
        if (!exists) {
            throw new ResourceNotFoundException("Rule not found with id : " + id);
        }
        repo.deleteById(id);
    }
}