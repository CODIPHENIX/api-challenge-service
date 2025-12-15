package com.apichallengeservice.dtos.challenge;

import com.apichallengeservice.enums.ChallengeCategory;
import com.apichallengeservice.enums.ChallengeDifficulty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UpdateChallengeDTO {

    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private ChallengeCategory category;
    @Enumerated(EnumType.STRING)
    private ChallengeDifficulty difficulty;
    private LocalDate startDate;
    private LocalDate endDate;
}
