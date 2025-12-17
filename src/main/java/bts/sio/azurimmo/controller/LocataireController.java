package bts.sio.azurimmo.controller;

import bts.sio.azurimmo.model.dto.LocataireDTO;
import bts.sio.azurimmo.service.LocataireService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/locataires")
@Tag(name = "Locataires", description = "Gestion des locataires")
public class LocataireController {

    @Autowired
    private LocataireService locataireService;

    @GetMapping
    @Operation(summary = "Récupère tous les locataires")
    public List<LocataireDTO> getAllLocataires() {
        return locataireService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupère tous les locataires selon l'ID")
    public ResponseEntity<LocataireDTO> getLocataireById(@PathVariable Long id) {
        Optional<LocataireDTO> dto = locataireService.findById(id);
        return dto.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Créer un locataire")
    public ResponseEntity<LocataireDTO> createLocataire(@RequestBody LocataireDTO locataireDTO) {
        LocataireDTO created = locataireService.save(locataireDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un locataire")
    public ResponseEntity<LocataireDTO> updateLocataire(@PathVariable Long id, @RequestBody LocataireDTO locataireDTO) {
        Optional<LocataireDTO> updated = locataireService.updateLocataire(id, locataireDTO);
        return updated.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un locataire")
    public ResponseEntity<Void> deleteLocataire(@PathVariable Long id) {
        if (locataireService.deleteLocataire(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}