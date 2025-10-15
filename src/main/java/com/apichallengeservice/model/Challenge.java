package com.apichallengeservice.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import com.apichallengeservice.enums.ChallengeCategory;
import com.apichallengeservice.enums.ChallengeDifficulty;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "challenges")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Column(length = 100)
    private String title;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ChallengeCategory category;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ChallengeDifficulty difficulty;

    @NotNull
    @Column(name = "creator_user_id")
    private Long creatorUserId; // Référence Groupe 1

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ChallengeObjective> objectives;

    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ChallengeRule> rules;

    @OneToOne(mappedBy = "challenge", cascade = CascadeType.ALL, orphanRemoval = true)
    private ChallengeReward reward;
}