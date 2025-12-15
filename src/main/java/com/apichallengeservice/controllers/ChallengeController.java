package com.apichallengeservice.controllers;

import com.apichallengeservice.dtos.challenge.RequestChallengeDTO;
import com.apichallengeservice.dtos.challenge.ResponseChallengeDTO;
import com.apichallengeservice.dtos.challenge.UpdateChallengeDTO;
import com.apichallengeservice.services.ChallengeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/challenges")
@Tag(name ="challenges", description = "Gestion complète du cycle de vie des défis sportifs, avec vérification de l'existence du créateur via l'API du Groupe 1")
public class ChallengeController {

    private final ChallengeService challengeService;

    @Operation(
            summary = "Créer un nouveau challenge",
            description = "Crée un défi sportif après validation des données envoyées (dates, catégorie, difficulté, etc.)"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Challenge créé avec succès",
                    content = @Content(schema = @Schema(implementation = ResponseChallengeDTO.class))),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "404", description = "Créateur du challenge introuvable")
    })
    @PostMapping
    public ResponseEntity<ResponseChallengeDTO> create(
            @Valid @RequestBody RequestChallengeDTO requestChallengeDTO){

     ResponseChallengeDTO challengeCreate = challengeService.createChallenge(requestChallengeDTO);

     return ResponseEntity.status(HttpStatus.CREATED).body(challengeCreate);
    }

    @Operation(
            summary = "Récupérer un challenge par ID",
            description = "Retourne les détails d’un challenge à partir de son identifiant"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Challenge trouvé",
                    content = @Content(schema = @Schema(implementation = ResponseChallengeDTO.class))),
            @ApiResponse(responseCode = "404", description = "Challenge introuvable")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ResponseChallengeDTO> findChallengeById(
            @PathVariable("id") Long id){

    return ResponseEntity.ok().body(challengeService.findChallengeByID(id));
    }

    @Operation(
            summary = "Lister tous les challenges",
            description = "Retourne une liste paginée de challenges"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des challenges récupérée avec succès")
    })
    @GetMapping
    public ResponseEntity<Page<ResponseChallengeDTO>> getAllChallenges(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return ResponseEntity.ok().body(challengeService.findAllChallenges(page,size));
    }

    @Operation(
            summary = "Mettre à jour un challenge",
            description = "Met à jour les informations d’un challenge existant"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Challenge mis à jour avec succès",
                    content = @Content(schema = @Schema(implementation = ResponseChallengeDTO.class))),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "404", description = "Challenge introuvable")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ResponseChallengeDTO> updateChallenge( @RequestBody UpdateChallengeDTO dto, @PathVariable("id") Long id ){
        return ResponseEntity.ok().body(challengeService.updateChallenges(dto,id));
    }

    @Operation(
            summary = "Supprimer un challenge",
            description = "Supprime définitivement un challenge à partir de son ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Challenge supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Challenge introuvable")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id ){

        challengeService.deleteChallenge(id);
        return ResponseEntity.noContent().build();

    }

}
