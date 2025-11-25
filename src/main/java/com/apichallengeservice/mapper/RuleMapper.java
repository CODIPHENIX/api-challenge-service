package com.apichallengeservice.mapper;

import com.apichallengeservice.dto.RuleDTO;
import com.apichallengeservice.dto.RuleCreateDTO;
import com.apichallengeservice.dto.RuleUpdateDTO;
import com.apichallengeservice.entity.ChallengeRule;

public class RuleMapper {

    public static RuleDTO toDTO(ChallengeRule rule) {
        RuleDTO dto = new RuleDTO();

        dto.setId(rule.getId());
        dto.setRuleDescription(rule.getRuleDescription());
        dto.setOrderIndex(rule.getOrderIndex());

        return dto;
    }

    public static ChallengeRule fromCreateDTO(RuleCreateDTO dto) {
        ChallengeRule rule = new ChallengeRule();
        rule.setRuleDescription(dto.getRuleDescription());
        rule.setOrderIndex(dto.getOrderIndex());
        return rule;
    }

    public static ChallengeRule updateEntity(ChallengeRule rule, RuleUpdateDTO dto) {
        rule.setRuleDescription(dto.getRuleDescription());
        rule.setOrderIndex(dto.getOrderIndex());
        return rule;
    }
}
