package bts.sio.azurimmo.controller;

import bts.sio.azurimmo.model.Appartement;
import bts.sio.azurimmo.service.AppartementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appartements")
@CrossOrigin(origins = "*") // Permet au HTML d'appeler l'API sans blocage CORS
@Tag(name = "Appartements", description = "Gestion des appartements")
public class AppartementController {
	
    @Autowired
    private AppartementService appartementService;

    @PostMapping("/")
    @Operation(summary = "Création d'un appartement")
    public Appartement createAppartement(@RequestBody Appartement appartement) {
        return appartementService.saveAppartement(appartement);
    }
    
    @GetMapping("")
    @Operation(summary = "Récupère tous les appartements")
    public List<Appartement> getAllAppartements() {
        return appartementService.getAppartements();
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Récupère un appartement par son ID")
    public Optional<Appartement> getAppartementById(@PathVariable Long id) {
        return appartementService.getAppartement(id);
    }
    
    @GetMapping("/ville/{ville}")
    @Operation(summary = "Récupère tous les appartements selon une ville")
    public List<Appartement> findByVille(@PathVariable String ville) {
        return appartementService.findByVille(ville);
    }
    
    @GetMapping("/batiment/{batimentId}")
    @Operation(summary = "Récupère tous les appartements selon un batiment")
    public List<Appartement> getAppartementsParBatiment(@PathVariable long batimentId) {
        return appartementService.getAppartementsParBatiment(batimentId);
    }

    // Changement ici : on ajoute /surface/ pour ne pas confondre avec l'ID
    @GetMapping("/surface/{surface}")
    @Operation(summary = "Récupère tous les appartements ayant une surface > à celle de l'URL ")
    public List<Appartement> getSurfaceGreaterThan(@PathVariable Double surface) {
        return appartementService.getSurfaceGreaterThan(surface);
    }
}