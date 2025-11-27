package com.apichallengeservice.controller;

import com.apichallengeservice.dto.RewardCreateDTO;
import com.apichallengeservice.dto.RewardDTO;
import com.apichallengeservice.dto.RewardUpdateDTO;
import com.apichallengeservice.service.ChallengeRewardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ChallengeRewardController {
    private final ChallengeRewardService rewardService;

    public ChallengeRewardController(ChallengeRewardService rewardService) {
        this.rewardService = rewardService;
    }

    @PostMapping("/challenges/{challengeId}/reward")
    public RewardDTO setReward(
            @PathVariable Long challengeId,
            @RequestBody RewardCreateDTO dto) {
        return rewardService.setReward(challengeId, dto);
    }

    @GetMapping("/challenges/{challengeId}/reward")
    public RewardDTO getReward(@PathVariable Long challengeId) {
        return rewardService.getReward(challengeId);
    }

    @PutMapping("/rewards/{id}")
    public RewardDTO updateReward(
            @PathVariable Long id,
            @RequestBody RewardUpdateDTO dto) {
        return rewardService.updateReward(id, dto);
    }
}
