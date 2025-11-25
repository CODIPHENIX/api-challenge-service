package com.apichallengeservice.dto;

import lombok.Data;

@Data
public class ObjectiveDTO {
    private Long id;
    private String objectiveType;
    private Double targetValue;
    private String unit;
}
