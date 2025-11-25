package com.apichallengeservice.dto;

import lombok.Data;

@Data
public class RewardDTO {
    private Long id;
    private Integer points;
    private Long badgeId;
    private String description;
}
