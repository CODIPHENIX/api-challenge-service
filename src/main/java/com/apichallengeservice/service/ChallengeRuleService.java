package com.apichallengeservice.service;

import org.springframework.stereotype.Service;

import com.apichallengeservice.entity.Challenge;
import com.apichallengeservice.entity.ChallengeRule;
import com.apichallengeservice.repository.ChallengeRepository;
import com.apichallengeservice.repository.ChallengeRuleRepository;
import java.util.List;


@Service
public class ChallengeRuleService {

    private final ChallengeRuleRepository ruleRepository;
    private final ChallengeRepository challengeRepository;

    public ChallengeRuleService(ChallengeRuleRepository ruleRepository,
                                ChallengeRepository challengeRepository) {
        this.ruleRepository = ruleRepository;
        this.challengeRepository = challengeRepository;
    }

    public ChallengeRule addRule(Long challengeId, ChallengeRule rule) {
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new RuntimeException("Challenge not found"));

        rule.setChallenge(challenge);
        return ruleRepository.save(rule);
    }

    public List<ChallengeRule> getRules(Long challengeId) {
        return ruleRepository.findByChallengeIdOrderByOrderIndexAsc(challengeId);
    }

    public ChallengeRule updateRule(Long id, ChallengeRule updated) {
        ChallengeRule rule = ruleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found"));

        rule.setRuleDescription(updated.getRuleDescription());
        rule.setOrderIndex(updated.getOrderIndex());

        return ruleRepository.save(rule);
    }

    public void deleteRule(Long id) {
        ruleRepository.deleteById(id);
    }
}
