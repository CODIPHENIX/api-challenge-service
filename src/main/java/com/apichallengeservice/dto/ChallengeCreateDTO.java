package com.apichallengeservice.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ChallengeCreateDTO {
    private String title;
    private String description;
    private String category;
    private String difficulty;
    private Long creatorUserId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isActive;
}
