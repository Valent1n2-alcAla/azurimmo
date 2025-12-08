package bts.sio.azurimmo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired; // NOUVEL IMPORT
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping; // NOUVEL IMPORT
import org.springframework.web.bind.annotation.RestController; // NOUVEL IMPORT

import bts.sio.azurimmo.model.dto.BatimentDTO;
import bts.sio.azurimmo.service.BatimentService;

@RestController // 1. Indique que c'est un contrôleur REST
@RequestMapping("/api/batiments") // 1. Définit l'URL de base
public class BatimentController {
    
    // 2. Injection de dépendance de l'instance du Service
    @Autowired
    private BatimentService batimentService; 
    
    // Chemin final : GET /api/batiments/{batimentId}
	@GetMapping("/{batimentId}") 
    public Optional <BatimentDTO> getBatimentDTO(@PathVariable Long batimentId) { // 3. Utilise Long
        
        // Appel sur l'instance injectée
        return batimentService.getBatimentDTO(batimentId);
    }
}