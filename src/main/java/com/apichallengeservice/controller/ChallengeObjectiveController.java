package com.apichallengeservice.controller;

import com.apichallengeservice.dto.ObjectiveCreateDTO;
import com.apichallengeservice.dto.ObjectiveDTO;
import com.apichallengeservice.dto.ObjectiveUpdateDTO;
import com.apichallengeservice.service.ChallengeObjectiveService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ChallengeObjectiveController {
    private final ChallengeObjectiveService objectiveService;

    public ChallengeObjectiveController(ChallengeObjectiveService objectiveService) {
        this.objectiveService = objectiveService;
    }

    @PostMapping("/challenges/{challengeId}/objectives")
    public ObjectiveDTO addObjective(
            @PathVariable Long challengeId,
            @RequestBody ObjectiveCreateDTO dto) {
        return objectiveService.addObjective(challengeId, dto);
    }

    @GetMapping("/challenges/{challengeId}/objectives")
    public List<ObjectiveDTO> getObjectives(@PathVariable Long challengeId) {
        return objectiveService.getObjectives(challengeId);
    }

    @PutMapping("/objectives/{id}")
    public ObjectiveDTO updateObjective(
            @PathVariable Long id,
            @RequestBody ObjectiveUpdateDTO dto) {
        return objectiveService.updateObjective(id, dto);
    }

    @DeleteMapping("/objectives/{id}")
    public void deleteObjective(@PathVariable Long id) {
        objectiveService.deleteObjective(id);
    }

}
