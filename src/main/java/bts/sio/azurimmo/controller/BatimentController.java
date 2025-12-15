package bts.sio.azurimmo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired; // NOUVEL IMPORT
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping; // NOUVEL IMPORT
import org.springframework.web.bind.annotation.RestController; // NOUVEL IMPORT

import bts.sio.azurimmo.model.dto.BatimentDTO;
import bts.sio.azurimmo.service.BatimentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController // 1. Indique que c'est un contrôleur REST
@RequestMapping("/api/batiments") // 1. Définit l'URL de base
@Tag(name = "Batiments", description = "Gestion des batiments")
public class BatimentController {
    
    // 2. Injection de dépendance de l'instance du Service
    @Autowired
    private BatimentService batimentService; 
    
    // Chemin final : GET /api/batiments/{batimentId}
	@GetMapping("/{batimentId}")
	@Operation(summary = "Récupère un batiment en fonction de son ID")
    public Optional <BatimentDTO> getBatimentDTO(@PathVariable Long batimentId) { // 3. Utilise Long
        
        // Appel sur l'instance injectée
        return batimentService.getBatimentDTO(batimentId);
    }
	
	@GetMapping("/re/{batimentId}")
	@Operation(summary = "Trouve un batiment en fonction de son DTO")
	public ResponseEntity<BatimentDTO> getBatimentReDTO(@PathVariable Long batimentId){
	    
	    // Utilisation de Long pour la cohérence des ID (recommandé)
	    return batimentService.getBatimentDTO(batimentId)
	                          
	                          // Si l'Optional contient une valeur (le DTO est trouvé)
	                          .map(ResponseEntity::ok) 
	                          
	                          // Sinon (le DTO est vide/non trouvé)
	                          .orElse(ResponseEntity.notFound().build()); 
	}
	
	 @GetMapping("/")
	 @Operation(summary = "Récupère tous les batiments")
	    public List<BatimentDTO> getAllBatiments() {
	        return batimentService.getBatimentsDTO(); 
	    }
	 
	 @PostMapping("/")
	 @Operation(summary = "Créer un batiment")
	    public ResponseEntity<BatimentDTO> createBatiment(@RequestBody BatimentDTO dto) {
	        BatimentDTO savedDTO = batimentService.saveBatimentDTO(dto);
	        return ResponseEntity.status(201).body(savedDTO); // 201 Created
	    }
}