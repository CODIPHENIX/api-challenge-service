package com.apichallengeservice.controller;

import com.apichallengeservice.dto.ChallengeCreateDTO;
import com.apichallengeservice.dto.ChallengeDTO;
import com.apichallengeservice.dto.ChallengeUpdateDTO;
import com.apichallengeservice.service.ChallengeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/challenges")
public class ChallengeController {
    private final ChallengeService challengeService;

    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @PostMapping()
    public ChallengeDTO createChallenge(@RequestBody ChallengeCreateDTO dto) {
        return challengeService.createChallenge(dto);
    }

    @GetMapping("/{id}")
    public ChallengeDTO getChallengeById(@PathVariable Long id) {
        return challengeService.getChallengeById(id);
    }

    @GetMapping
    public Page<ChallengeDTO> getAllChallenges(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return challengeService.getAllChallenges(pageable);
    }

    @PutMapping("/{id}")
    public ChallengeDTO updateChallenge(
            @PathVariable Long id,
            @RequestBody ChallengeUpdateDTO dto) {
        return challengeService.updateChallenge(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteChallenge(@PathVariable Long id) {
        challengeService.deleteChallenge(id);
    }

    // -------- Filters -------- //

    @GetMapping("/active")
    public Page<ChallengeDTO> getActiveChallenges(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return challengeService.getActive(PageRequest.of(page, size));
    }

    @GetMapping("/category/{category}")
    public Page<ChallengeDTO> getByCategory(
            @PathVariable String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return challengeService.getByCategory(category, PageRequest.of(page, size));
    }

    @GetMapping("/difficulty/{difficulty}")
    public Page<ChallengeDTO> getByDifficulty(
            @PathVariable String difficulty,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return challengeService.getByDifficulty(difficulty, PageRequest.of(page, size));
    }

    @GetMapping("/user/{creatorUserId}")
    public Page<ChallengeDTO> getByCreator(
            @PathVariable Long creatorUserId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return challengeService.getByCreator(creatorUserId, PageRequest.of(page, size));
    }

}
