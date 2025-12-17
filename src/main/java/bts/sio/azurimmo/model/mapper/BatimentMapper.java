package bts.sio.azurimmo.model.mapper;

import bts.sio.azurimmo.model.Batiment;
import bts.sio.azurimmo.model.dto.BatimentDTO;

public class BatimentMapper {

    public static BatimentDTO toDTO(Batiment entity) {
        if (entity == null) {
            return null;
        }

        BatimentDTO dto = new BatimentDTO();
        // CETTE LIGNE EST CRUCIALE POUR AFFICHER LA RÉFÉRENCE
        dto.setId(entity.getId()); 
        
        dto.setAdresse(entity.getAdresse());
        dto.setVille(entity.getVille());
        
        // Note : Si vous avez besoin des appartements dans le DTO, 
        // il faudra aussi les mapper ici plus tard.
        
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