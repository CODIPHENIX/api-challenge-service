package com.apichallengeservice.controller;

import com.apichallengeservice.entity.ChallengeRule;
import com.apichallengeservice.service.ChallengeRuleService;
import com.apichallengeservice.dto.RuleCreateDTO;
import com.apichallengeservice.dto.RewardUpdateDTO;
import com.apichallengeservice.dto.RuleDTO;
import com.apichallengeservice.dto.RuleUpdateDTO;

import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ChallengeRuleController {
    private final ChallengeRuleService ruleService;

    public ChallengeRuleController(ChallengeRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping("/challenges/{challengeId}/rules")
    public RuleDTO addRule(
        @PathVariable Long challengeId,
        @RequestBody RuleCreateDTO dto
    ) {
        return ruleService.addRule(challengeId, dto);
    }

    @GetMapping("/challenges/{challengeId}/rules")
    public List<RuleDTO> getRules(@PathVariable Long challengeId) {
        return ruleService.getRules(challengeId);
    }

    @PutMapping("/rules/{id}")
    public RuleDTO updatRule(
        @PathVariable Long id,
        @RequestBody RuleUpdateDTO dto
    ) {
        return ruleService.updateRule(id, dto);
    }

    @DeleteMapping("/rules/{id}")
    public void deleteRule(@PathVariable Long id) {
        ruleService.deleteRule(id);
    }
}
