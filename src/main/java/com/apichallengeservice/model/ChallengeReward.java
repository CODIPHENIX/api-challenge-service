package com.apichallengeservice.model;
import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "challenge_rewards")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeReward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "challenge_id", referencedColumnName = "id")
    private Challenge challenge;

    @Column(nullable = false)
    private Integer points;

    @Column(name = "badge_id")
    private Long badgeId; // Référence vers un badge du Groupe 1

    @Column(length = 500)
    private String description;
}