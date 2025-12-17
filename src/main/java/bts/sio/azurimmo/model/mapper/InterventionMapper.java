package bts.sio.azurimmo.model.mapper;

import bts.sio.azurimmo.model.dto.InterventionDTO;
import bts.sio.azurimmo.model.Intervention;

public class InterventionMapper {

    
    public static InterventionDTO toDTO(Intervention entity) {
        if (entity == null) return null;

        InterventionDTO dto = new InterventionDTO();
        dto.setId(entity.getId());
        dto.setDatePrevue(entity.getDatePrevue());
        dto.setDateRealise(entity.getDateRealise());
        dto.setDescription(entity.getDescription());

        
        if (entity.getAppartement() != null) {
            dto.setAppartement(AppartementMapper.toDTO(entity.getAppartement()));
        }

        return dto;
    }

    
    public static Intervention toEntity(InterventionDTO dto) {
        if (dto == null) return null;

        Intervention entity = new Intervention();
        if (dto.getId() != null) entity.setId(dto.getId());
        entity.setDatePrevue(dto.getDatePrevue());
        entity.setDateRealise(dto.getDateRealise());
        entity.setDescription(dto.getDescription());

        
        
        return entity;
    }
}