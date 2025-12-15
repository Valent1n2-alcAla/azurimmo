package bts.sio.azurimmo.service;

import bts.sio.azurimmo.model.Loyer;
import bts.sio.azurimmo.model.dto.LoyerDTO;
import bts.sio.azurimmo.model.dto.ContratDTO; // Import du DTO simple
import bts.sio.azurimmo.repository.LoyerRepository;
import bts.sio.azurimmo.repository.ContratRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoyerService {
    @Autowired private LoyerRepository loyerRepository;
    @Autowired private ContratRepository contratRepository;

    public List<LoyerDTO> findAll() {
        return loyerRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<LoyerDTO> findById(Long id) {
        return loyerRepository.findById(id).map(this::toDTO);
    }

    public LoyerDTO save(LoyerDTO dto) {
        Loyer entity = toEntity(dto);
        Loyer savedEntity = loyerRepository.save(entity);
        return toDTO(savedEntity);
    }
    
    // ----------------------------------------------------------------------
    // LOGIQUE DE CONVERSION (MISE À JOUR pour gérer les objets imbriqués)
    // ----------------------------------------------------------------------

    private Loyer toEntity(LoyerDTO dto) {
        Loyer entity = new Loyer();
        if (dto.getId() != null) entity.setId(dto.getId());
        entity.setDatePaiement(dto.getDatePaiement());
        entity.setMontantPaye(dto.getMontantPaye());

        // Gérer la relation : on récupère l'ID à l'intérieur de l'objet DTO imbriqué
        if (dto.getContrat() != null && dto.getContrat().getId() != null) {
            contratRepository.findById(dto.getContrat().getId()).ifPresent(entity::setContrat);
        }
        return entity;
    }

    private LoyerDTO toDTO(Loyer entity) {
        LoyerDTO dto = new LoyerDTO();
        dto.setId(entity.getId());
        dto.setDatePaiement(entity.getDatePaiement());
        dto.setMontantPaye(entity.getMontantPaye());
        
        // Convertir l'Entité liée en DTO Simple pour la réponse (GET)
        if (entity.getContrat() != null) {
            ContratDTO contratDto = new ContratDTO();
            contratDto.setId(entity.getContrat().getId());
            dto.setContrat(contratDto);
        }
        return dto;
    }
}