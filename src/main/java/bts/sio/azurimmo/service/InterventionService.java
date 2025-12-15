package bts.sio.azurimmo.service;

import bts.sio.azurimmo.model.Intervention;
import bts.sio.azurimmo.model.dto.InterventionDTO;
import bts.sio.azurimmo.model.dto.AppartementDTO; // Import du DTO simple
import bts.sio.azurimmo.repository.InterventionRepository;
import bts.sio.azurimmo.repository.AppartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InterventionService {
    @Autowired private InterventionRepository interventionRepository;
    @Autowired private AppartementRepository appartementRepository;

    public List<InterventionDTO> findAll() {
        return interventionRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<InterventionDTO> findById(Long id) {
        return interventionRepository.findById(id).map(this::toDTO);
    }

    public InterventionDTO save(InterventionDTO dto) {
        Intervention entity = toEntity(dto);
        Intervention savedEntity = interventionRepository.save(entity);
        return toDTO(savedEntity);
    }
    
    // ----------------------------------------------------------------------
    // LOGIQUE DE CONVERSION (MISE À JOUR pour gérer les objets imbriqués)
    // ----------------------------------------------------------------------

    private Intervention toEntity(InterventionDTO dto) {
        Intervention entity = new Intervention();
        if (dto.getId() != null) entity.setId(dto.getId());
        entity.setType(dto.getType());
        entity.setDatePrevue(dto.getDatePrevue());
        entity.setDateRealise(dto.getDateRealise());
        entity.setDescription(dto.getDescription());

        // Gérer la relation : on récupère l'ID à l'intérieur de l'objet DTO imbriqué
        if (dto.getAppartement() != null && dto.getAppartement().getId() != null) {
            appartementRepository.findById(dto.getAppartement().getId()).ifPresent(entity::setAppartement);
        }
        return entity;
    }

    private InterventionDTO toDTO(Intervention entity) {
        InterventionDTO dto = new InterventionDTO();
        dto.setId(entity.getId());
        dto.setType(entity.getType());
        dto.setDatePrevue(entity.getDatePrevue());
        dto.setDateRealise(entity.getDateRealise());
        dto.setDescription(entity.getDescription());
        
        // Convertir l'Entité liée en DTO Simple pour la réponse (GET)
        if (entity.getAppartement() != null) {
            AppartementDTO appartDto = new AppartementDTO();
            appartDto.setId(entity.getAppartement().getId());
            dto.setAppartement(appartDto);
        }
        return dto;
    }
}