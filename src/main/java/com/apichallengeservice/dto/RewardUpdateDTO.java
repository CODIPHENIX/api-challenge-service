package com.apichallengeservice.dto;

import lombok.Data;

@Data
public class RewardUpdateDTO {
    private Integer points;
    private Long badgeId;
    private String description;
}
