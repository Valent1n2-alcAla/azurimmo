package bts.sio.azurimmo.model.mapper;

import bts.sio.azurimmo.model.Batiment;
import bts.sio.azurimmo.model.dto.BatimentDTO;
import java.util.stream.Collectors;

public class BatimentMapper {

    public static BatimentDTO toDTO(Batiment entity) {
        if (entity == null) {
            return null;
        }

        BatimentDTO dto = new BatimentDTO();
        dto.setId(entity.getId()); 
        dto.setAdresse(entity.getAdresse());
        dto.setVille(entity.getVille());
        
        // --- LA CORRECTION EST ICI ---
        if (entity.getAppartements() != null) {
            dto.setAppartements(entity.getAppartements().stream()
                .map(AppartementMapper::toDTO) // On transforme chaque Appartement en AppartementDTO
                .collect(Collectors.toList()));
        }
        // -----------------------------
        
        return dto;
    }

    public static Batiment toEntity(BatimentDTO dto) {
        if (dto == null) {
            return null;
        }

        Batiment entity = new Batiment();
        entity.setId(dto.getId());
        entity.setAdresse(dto.getAdresse());
        entity.setVille(dto.getVille());

        return entity;
    }
}