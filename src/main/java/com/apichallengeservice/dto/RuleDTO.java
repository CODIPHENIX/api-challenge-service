package com.apichallengeservice.dto;

import lombok.Data;

@Data
public class RuleDTO {
    private Long id;
    private String ruleDescription;
    private Integer orderIndex;
}
