package com.apichallengeservice.dto;

import com.apichallengeservice.dto.client.UserProfileDTO;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class ChallengeDTO {
    private Long id;
    private String title;
    private String description;
    private String category;
    private String difficulty;
    private Long creatorUserId;

    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isActive;

    private UserProfileDTO creatorProfile;

    private List<ObjectiveDTO> objectives;
    private List<RuleDTO> rules;
    private RewardDTO reward;
}
