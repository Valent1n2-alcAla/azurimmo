package bts.sio.azurimmo.service;

import bts.sio.azurimmo.model.Contrat;
import bts.sio.azurimmo.model.Loyer;

import bts.sio.azurimmo.model.dto.LoyerDTO;
import bts.sio.azurimmo.model.mapper.LoyerMapper;
import bts.sio.azurimmo.model.dto.ContratDTO; // Import du DTO simple
import bts.sio.azurimmo.repository.LoyerRepository;
import jakarta.transaction.Transactional;
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
    


    private Loyer toEntity(LoyerDTO dto) {
        Loyer entity = new Loyer();
        if (dto.getId() != null) entity.setId(dto.getId());
        entity.setDatePaiement(dto.getDatePaiement());
        entity.setMontantPaye(dto.getMontantPaye());

        
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
        
        
        if (entity.getContrat() != null) {
            ContratDTO contratDto = new ContratDTO();
            contratDto.setId(entity.getContrat().getId());
            dto.setContrat(contratDto);
        }
        return dto;
    }
    @Transactional
    public Optional<LoyerDTO> updateLoyer(Long id, LoyerDTO dtoDetails) {
        return loyerRepository.findById(id)
            .map(existingLoyer -> {
              
                if (dtoDetails.getMontantPaye() != null) 
                    existingLoyer.setMontantPaye(dtoDetails.getMontantPaye());
                if (dtoDetails.getDatePaiement() != null) 
                    existingLoyer.setDatePaiement(dtoDetails.getDatePaiement());
                
                
                if (dtoDetails.getContrat() != null && dtoDetails.getContrat().getId() != null) {
                    Contrat contrat = contratRepository.findById(dtoDetails.getContrat().getId())
                        .orElseThrow(() -> new IllegalArgumentException("Contrat non trouvé."));
                    existingLoyer.setContrat(contrat);
                }
                
                Loyer updatedLoyer = loyerRepository.save(existingLoyer);
                return LoyerMapper.toDTO(updatedLoyer);
            });
    }
    
    @Transactional
    public boolean deleteLoyer(Long id) { 
        if (loyerRepository.existsById(id)) {
            loyerRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
}