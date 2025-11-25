package com.apichallengeservice.controller;

import com.apichallengeservice.entity.ChallengeReward;
import com.apichallengeservice.service.ChallengeRewardService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api")
public class ChallengeRewardController {
    private final ChallengeRewardService rewardService;

    public ChallengeRewardController(ChallengeRewardService rewardService) {
        this.rewardService = rewardService;
    }

    @PostMapping("/challenges/{challengeId}/reward")
    public ChallengeReward setReward(
        @PathVariable Long challengeId,
        @RequestBody ChallengeReward reward
    ) {
        return rewardService.setReward(challengeId, reward);
    }

    @GetMapping("/challenges/{challengeId}/reward")
    public ChallengeReward getReward(@PathVariable Long challengeId) {
        return rewardService.getReward(challengeId);
    }

    @PutMapping("/rewards/{id}")
    public ChallengeReward updatReward(
        @PathVariable Long id,
        @RequestBody ChallengeReward updated
    ) {
        return rewardService.updateReward(id, updated);
    }
}
