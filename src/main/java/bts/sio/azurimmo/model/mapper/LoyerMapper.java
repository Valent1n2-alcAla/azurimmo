package bts.sio.azurimmo.model.mapper;

import bts.sio.azurimmo.model.dto.LoyerDTO;
import bts.sio.azurimmo.model.Loyer;

public class LoyerMapper {

    public static LoyerDTO toDTO(Loyer entity) {
        if (entity == null) return null;

        LoyerDTO dto = new LoyerDTO();
        dto.setId(entity.getId());
        dto.setDatePaiement(entity.getDatePaiement());
        dto.setMontantPaye(entity.getMontantPaye());

        
        if (entity.getContrat() != null) {
           
            dto.setContrat(ContratMapper.toDTO(entity.getContrat()));
        }

        return dto;
    }

    
    public static Loyer toEntity(LoyerDTO dto) {
        if (dto == null) return null;

        Loyer entity = new Loyer();
        if (dto.getId() != null) entity.setId(dto.getId());
        entity.setDatePaiement(dto.getDatePaiement());
        entity.setMontantPaye(dto.getMontantPaye());

   
        
        return entity;
    }
}