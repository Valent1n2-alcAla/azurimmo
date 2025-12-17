package bts.sio.azurimmo.controller;

import bts.sio.azurimmo.model.dto.ContratDTO;
import bts.sio.azurimmo.service.ContratService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contrats")
@Tag(name = "Contrats", description = "Gestion des Contrats")
public class ContratController {
    
    @Autowired
    private ContratService contratService;

    @GetMapping("/")
    @Operation(summary = "Récupère tous les contrats")
    public List<ContratDTO> getAllContrats() {
        return contratService.getAllContrats();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupère tous les contrats selon leur ID")
    public ResponseEntity<ContratDTO> getContratById(@PathVariable Long id) {
        Optional<ContratDTO> dto = contratService.getContratById(id);
        return dto.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Créer un contrat")
    public ResponseEntity<ContratDTO> createContrat(@RequestBody ContratDTO contratDTO) {
        ContratDTO created = contratService.createContrat(contratDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un contrat existant")
    public ResponseEntity<ContratDTO> updateContrat(@PathVariable Long id, @RequestBody ContratDTO contratDTO) {
        Optional<ContratDTO> updated = contratService.updateContrat(id, contratDTO);
        return updated.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un contrat par ID")
    public ResponseEntity<Void> deleteContrat(@PathVariable Long id) {
        if (contratService.deleteContrat(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}