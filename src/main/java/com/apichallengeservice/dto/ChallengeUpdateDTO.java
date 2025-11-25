package com.apichallengeservice.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ChallengeUpdateDTO {
    private String title;
    private String description;
    private String category;
    private String difficulty;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isActive;
}