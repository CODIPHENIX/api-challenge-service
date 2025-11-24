package com.apichallengeservice.dto.client;

import lombok.Data;

@Data
public class UserProfileDTO {
    private Long userId;
    private String fullname;
    private String level;
    private Integer points;
}