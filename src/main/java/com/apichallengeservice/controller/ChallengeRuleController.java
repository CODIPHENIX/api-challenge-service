package com.apichallengeservice.controller;

import com.apichallengeservice.entity.ChallengeRule;
import com.apichallengeservice.service.ChallengeRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ChallengeRuleController {
    private final ChallengeRuleService ruleService;

    public ChallengeRuleController(ChallengeRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping("/challenges/{challengeId}/rules")
    public ChallengeRule addRule(
        @PathVariable Long challengeId,
        @RequestBody ChallengeRule rule
    ) {
        return ruleService.addRule(challengeId, rule);
    }

    @GetMapping("/challenges/{challengeId}/rules")
    public List<ChallengeRule> getRules(@PathVariable Long challengeId) {
        return ruleService.getRules(challengeId);
    }

    @PutMapping("/rules/{id}")
    public ChallengeRule updatRule(
        @PathVariable Long id,
        @RequestBody ChallengeRule updated
    ) {
        return ruleService.updateRule(id, updated);
    }

    @DeleteMapping("/rules/{id}")
    public void deleteRule(@PathVariable Long id) {
        ruleService.deleteRule(id);
    }
}
