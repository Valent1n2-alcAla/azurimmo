package bts.sio.azurimmo.controller;

import bts.sio.azurimmo.model.Appartement;
import bts.sio.azurimmo.service.AppartementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/appartements")
@Tag(name = "Appartements", description = "Gestion des appartements")
public class AppartementController {
	
   @Autowired
    private AppartementService appartementService;

	
    @PostMapping("/")
    @Operation(summary = "Création d'un appartement")
    public Appartement createAppartement(@RequestBody Appartement appartement) {
        return appartementService.saveAppartement(appartement);
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
    @GetMapping("/{surface}")
    @Operation(summary = "Récupère tous les appartements ayant une surface > à celle de l'URL ")
    public List<Appartement> getSurfaceGreaterThan(@PathVariable Double surface) {
            return appartementService.getSurfaceGreaterThan(surface);
     }
}