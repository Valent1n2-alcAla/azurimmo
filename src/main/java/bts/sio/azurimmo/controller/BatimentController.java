package bts.sio.azurimmo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bts.sio.azurimmo.model.dto.BatimentDTO;
import bts.sio.azurimmo.service.BatimentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/batiments")
@Tag(name = "Batiments", description = "Gestion des batiments")
public class BatimentController {
    
    @Autowired
    private BatimentService batimentService; 
    
    @GetMapping("/{batimentId}")
    @Operation(summary = "Récupère un batiment en fonction de son ID")
    public Optional <BatimentDTO> getBatimentDTO(@PathVariable Long batimentId) { 
        return batimentService.getBatimentDTO(batimentId);
    }
    
    @GetMapping("/re/{batimentId}")
    @Operation(summary = "Trouve un batiment en fonction de son DTO")
    public ResponseEntity<BatimentDTO> getBatimentReDTO(@PathVariable Long batimentId){
        return batimentService.getBatimentDTO(batimentId)
                              .map(ResponseEntity::ok) 
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
         return ResponseEntity.status(201).body(savedDTO);
     }
    
     @PutMapping("/{id}")
     @Operation(summary = "Mettre à jour un bâtiment")
     public ResponseEntity<BatimentDTO> updateBatiment(@PathVariable Long id, @RequestBody BatimentDTO batimentDTO) {
         Optional<BatimentDTO> updated = batimentService.updateBatiment(id, batimentDTO);
         return updated.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
     }

     @DeleteMapping("/{id}")
     @Operation(summary = "Supprimer un bâtiment")
     public ResponseEntity<Void> deleteBatiment(@PathVariable Long id) {
         if (batimentService.deleteBatiment(id)) {
             return ResponseEntity.noContent().build();
         }
         return ResponseEntity.notFound().build();
     }
}