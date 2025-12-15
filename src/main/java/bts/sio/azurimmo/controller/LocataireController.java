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

    @GetMapping // GET /api/locataires
    @Operation(summary = "Récupère tous les locataires")
    public List<LocataireDTO> getAllLocataires() {
        return locataireService.findAll();
    }

    @GetMapping("/{id}") // GET /api/locataires/1
    @Operation(summary = "Récupère tous les locataires selon l'ID")
    public ResponseEntity<LocataireDTO> getLocataireById(@PathVariable Long id) {
        Optional<LocataireDTO> dto = locataireService.findById(id);
        return dto.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping // POST /api/locataires
    @Operation(summary = "Créer un locataire")
    public ResponseEntity<LocataireDTO> createLocataire(@RequestBody LocataireDTO locataireDTO) {
        LocataireDTO created = locataireService.save(locataireDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
}