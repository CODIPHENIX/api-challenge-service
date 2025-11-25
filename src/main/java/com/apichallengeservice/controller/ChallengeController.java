package com.apichallengeservice.controller;

import com.apichallengeservice.entity.Challenge;
import com.apichallengeservice.service.ChallengeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/challenges")
public class ChallengeController {
    private final ChallengeService challengeService;

    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @PostMapping()
    public Challenge createChallenge(@RequestBody Challenge challenge) {
        return challengeService.createChallenge(challenge);
    }
    
    @GetMapping("/{id}")
    public Challenge getChallengeById(@PathVariable Long id) {
        return challengeService.getChallengeById(id);
    }

    @GetMapping
    public Page<Challenge> getAllChallenges(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return challengeService.getAllChallenges(pageable);
    }

    @PutMapping("/{id}")
    public Challenge updateChallenge(
        @PathVariable Long id,
        @RequestBody Challenge updateChallenge
    ) {
        return challengeService.updateChallenge(id, updateChallenge);
    }

    @DeleteMapping("/{id}")
    public void deleteChallenge(@PathVariable Long id) {
        challengeService.deleteChallenge(id);
    }

    // -------- Filters -------- //

    @GetMapping("/active")
    public Page<Challenge> getActiveChallenges(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        return challengeService.getActive(PageRequest.of(page, size));
    }
    
    @GetMapping("/category/{category}")
    public Page<Challenge> getByCategory(
        @PathVariable String category,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        return challengeService.getByCategory(category, PageRequest.of(page, size));
    }

    @GetMapping("/difficulty/{difficulty}")
    public Page<Challenge> getByDifficulty(
        @PathVariable String difficulty,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        return challengeService.getByCategory(difficulty, PageRequest.of(page, size));
    }

    @GetMapping("/user/{creatorUserId}")
    public Page<Challenge> getByCreator(
        @PathVariable Long creatorUserId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        return challengeService.getByCreator(creatorUserId, PageRequest.of(page, size));
    }
    
}
