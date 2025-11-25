package com.apichallengeservice.controller;

import com.apichallengeservice.entity.ChallengeObjective;
import com.apichallengeservice.service.ChallengeObjectiveService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api")
public class ChallengeObjectiveController {
    private final ChallengeObjectiveService objectiveService;

    public ChallengeObjectiveController(ChallengeObjectiveService objectiveService) {
        this.objectiveService = objectiveService;
    }

    @PostMapping("/challenges/{challengeId}/objectives")
    public  ChallengeObjective addObjective(
        @PathVariable Long challengeId,
        @RequestBody ChallengeObjective objective
    ) {
        return objectiveService.addObjective(challengeId, objective);
    }
    
    @GetMapping("challenges/{challengeId}/objectives")
    public ChallengeObjective updateObjective(
        @PathVariable Long id,
        @RequestBody ChallengeObjective updated
    ) {
        return objectiveService.updateObjective(id, updated);
    }

    @DeleteMapping("/objectives/{id}")
    public void deleteObjective(@PathVariable Long id) {
        objectiveService.deleteObjective(id);
    }
    
}
