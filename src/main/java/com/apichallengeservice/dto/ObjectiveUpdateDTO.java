package com.apichallengeservice.dto;

import lombok.Data;

@Data
public class ObjectiveUpdateDTO {
    private String objectiveType;
    private Double targetValue;
    private String unit;
}
