package bts.sio.azurimmo.model.mapper;

import bts.sio.azurimmo.model.Locataire;
import bts.sio.azurimmo.model.dto.LocataireDTO;

public class LocataireMapper {

    public static LocataireDTO toDTO(Locataire entity) {
        if (entity == null) return null;
        LocataireDTO dto = new LocataireDTO();
        dto.setId(entity.getId());
        dto.setNom(entity.getNom());
        dto.setPrenom(entity.getPrenom());
        dto.setTelephone(entity.getTelephone());
        dto.setMail(entity.getMail());
        dto.setDateNaissance(entity.getDateNaissance());
        return dto;
    }

    public static Locataire toEntity(LocataireDTO dto) {
        if (dto == null) return null;
        Locataire entity = new Locataire();
        // On ne met pas l'ID ici si c'est pour une création, car il est généré par la BDD
        entity.setNom(dto.getNom());
        entity.setPrenom(dto.getPrenom());
        entity.setTelephone(dto.getTelephone());
        entity.setMail(dto.getMail());
        entity.setDateNaissance(dto.getDateNaissance());
        return entity;
    }
}