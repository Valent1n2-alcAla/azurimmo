package bts.sio.azurimmo.controller;

import bts.sio.azurimmo.model.dto.LoyerDTO;
import bts.sio.azurimmo.service.LoyerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/loyers")
@Tag(name = "Loyers", description = "Gestion des loyers")
public class LoyerController {
    @Autowired
    private LoyerService loyerService;

    @GetMapping
    @Operation(summary = "Récupère tous les loyers")
    public List<LoyerDTO> getAllLoyers() {
        return loyerService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupère les loyers par rapport à leur ID")
    public ResponseEntity<LoyerDTO> getLoyerById(@PathVariable Long id) {
        Optional<LoyerDTO> dto = loyerService.findById(id);
        return dto.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Créer un loyer")
    public ResponseEntity<LoyerDTO> createLoyer(@RequestBody LoyerDTO loyerDTO) {
        LoyerDTO created = loyerService.save(loyerDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
}