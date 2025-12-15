package com.apichallengeservice.dtos.challenge;

import com.apichallengeservice.enums.ChallengeCategory;
import com.apichallengeservice.enums.ChallengeDifficulty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class RequestChallengeDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ChallengeCategory category;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ChallengeDifficulty difficulty;

    private LocalDate startDate;
    @NotNull(message = "user id absent")
    private Long creatorUserId; // Référence Groupe 1

    private LocalDate endDate;
}
