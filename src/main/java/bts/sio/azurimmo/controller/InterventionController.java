package bts.sio.azurimmo.controller;

import bts.sio.azurimmo.model.dto.InterventionDTO;
import bts.sio.azurimmo.service.InterventionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/interventions")
@Tag(name = "Interventions", description = "Gestion des interventions")
public class InterventionController {

    @Autowired
    private InterventionService interventionService;

    @GetMapping("/")
    @Operation(summary = "Récupère toutes les interventions")
    public List<InterventionDTO> getAllInterventions() {
        return interventionService.getAllInterventions();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupère les interventions selon leur ID")
    public ResponseEntity<InterventionDTO> getInterventionById(@PathVariable Long id) {
        Optional<InterventionDTO> dto = interventionService.getInterventionById(id);
        return dto.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Créer une intervention")
    public ResponseEntity<InterventionDTO> createIntervention(@RequestBody InterventionDTO interventionDTO) {
        try {
            InterventionDTO created = interventionService.createIntervention(interventionDTO);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour une intervention")
    public ResponseEntity<InterventionDTO> updateIntervention(@PathVariable Long id, @RequestBody InterventionDTO interventionDTO) {
        try {
            Optional<InterventionDTO> updated = interventionService.updateIntervention(id, interventionDTO);
            return updated.map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une intervention")
    public ResponseEntity<Void> deleteIntervention(@PathVariable Long id) {
        if (interventionService.deleteIntervention(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}