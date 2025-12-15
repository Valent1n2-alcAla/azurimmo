package bts.sio.azurimmo.service;

import bts.sio.azurimmo.model.Contrat;
import bts.sio.azurimmo.model.dto.ContratDTO;
import bts.sio.azurimmo.model.dto.AppartementDTO; // Import du DTO simple
import bts.sio.azurimmo.model.dto.LocataireDTO;   // Import du DTO simple
import bts.sio.azurimmo.repository.ContratRepository;
import bts.sio.azurimmo.repository.AppartementRepository;
import bts.sio.azurimmo.repository.LocataireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContratService {
    @Autowired private ContratRepository contratRepository;
    @Autowired private AppartementRepository appartementRepository;
    @Autowired private LocataireRepository locataireRepository;

    public List<ContratDTO> findAll() {
        return contratRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<ContratDTO> findById(Long id) {
        return contratRepository.findById(id).map(this::toDTO);
    }

    public ContratDTO save(ContratDTO dto) {
        Contrat entity = toEntity(dto);
        Contrat savedEntity = contratRepository.save(entity);
        return toDTO(savedEntity);
    }
    
    // ----------------------------------------------------------------------
    // LOGIQUE DE CONVERSION (MISE À JOUR pour gérer les objets imbriqués)
    // ----------------------------------------------------------------------

    private Contrat toEntity(ContratDTO dto) {
        Contrat entity = new Contrat();
        if (dto.getId() != null) entity.setId(dto.getId());
        entity.setDateDebut(dto.getDateDebut());
        entity.setDateFin(dto.getDateFin());
        entity.setMontantBrut(dto.getMontantBrut());
        entity.setMontantCharge(dto.getMontantCharge());

        // Gérer les relations : on récupère l'ID à l'intérieur de l'objet DTO imbriqué
        if (dto.getAppartement() != null && dto.getAppartement().getId() != null) {
            appartementRepository.findById(dto.getAppartement().getId())
                .ifPresent(entity::setAppartement);
        }
        if (dto.getLocataire() != null && dto.getLocataire().getId() != null) {
            locataireRepository.findById(dto.getLocataire().getId())
                .ifPresent(entity::setLocataire);
        }
        return entity;
    }

    private ContratDTO toDTO(Contrat entity) {
        ContratDTO dto = new ContratDTO();
        dto.setId(entity.getId());
        dto.setDateDebut(entity.getDateDebut());
        dto.setDateFin(entity.getDateFin());
        dto.setMontantBrut(entity.getMontantBrut());
        dto.setMontantCharge(entity.getMontantCharge());
        
        // Convertir l'Entité liée en DTO Simple pour la réponse (GET)
        if (entity.getAppartement() != null) {
            AppartementDTO appartDto = new AppartementDTO();
            appartDto.setId(entity.getAppartement().getId());
            dto.setAppartement(appartDto);
        }
        if (entity.getLocataire() != null) {
            LocataireDTO locDto = new LocataireDTO();
            locDto.setId(entity.getLocataire().getId());
            dto.setLocataire(locDto);
        }
        return dto;
    }
}