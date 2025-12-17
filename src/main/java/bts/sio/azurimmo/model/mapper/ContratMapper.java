package bts.sio.azurimmo.model.mapper;

import bts.sio.azurimmo.model.dto.ContratDTO;
import bts.sio.azurimmo.model.Contrat;



public class ContratMapper {

   
    public static ContratDTO toDTO(Contrat entity) {
        if (entity == null) return null;

        ContratDTO dto = new ContratDTO();
        dto.setId(entity.getId());
        dto.setDateDebut(entity.getDateDebut());
        dto.setDateFin(entity.getDateFin());
        dto.setMontantBrut(entity.getMontantBrut());
        dto.setMontantCharge(entity.getMontantCharge());

        
        if (entity.getAppartement() != null) {
            
            dto.setAppartement(AppartementMapper.toDTO(entity.getAppartement()));
        }
        if (entity.getLocataire() != null) {
           
            dto.setLocataire(LocataireMapper.toDTO(entity.getLocataire()));
        }

        return dto;
    }

    
    public static Contrat toEntity(ContratDTO dto) {
        if (dto == null) return null;

        Contrat entity = new Contrat();
        if (dto.getId() != null) entity.setId(dto.getId());
        entity.setDateDebut(dto.getDateDebut());
        entity.setDateFin(dto.getDateFin());
        entity.setMontantBrut(dto.getMontantBrut());
        entity.setMontantCharge(dto.getMontantCharge());

        

        return entity;
    }
}