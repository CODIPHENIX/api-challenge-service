package com.apichallengeservice.dto;

import lombok.Data;

@Data
public class RewardCreateDTO {
    private Integer points;
    private Long badgeId;
    private String description;
}
