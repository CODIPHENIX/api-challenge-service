package com.apichallengeservice.dtos.challenge;

import com.apichallengeservice.enums.ChallengeCategory;
import com.apichallengeservice.enums.ChallengeDifficulty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class ResponseChallengeDTO {
    private Long id;
    private String title;
    private String description;
    private ChallengeCategory category;
    private ChallengeDifficulty difficulty;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long creatorUserId; // Référence Groupe 1
    private String creatorName; // Référence Groupe 1
    private String creatorLevel; // Référence Groupe 1
    private boolean isActive;
}
