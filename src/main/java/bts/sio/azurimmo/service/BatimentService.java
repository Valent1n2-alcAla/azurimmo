package bts.sio.azurimmo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import bts.sio.azurimmo.model.Batiment;
import bts.sio.azurimmo.model.dto.BatimentDTO;
import bts.sio.azurimmo.repository.BatimentRepository;
import jakarta.transaction.Transactional;
import bts.sio.azurimmo.model.mapper.BatimentMapper;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 

@Service
public class BatimentService {
    
    // 1. Injection de l'INSTANCE du Repository
    @Autowired 
    private BatimentRepository batimentRepository; 

    public Optional<BatimentDTO> getBatimentDTO(Long id) {
        return batimentRepository.findById(id) 
                                 .map(BatimentMapper::toDTO);
    }
    
    public List<BatimentDTO> getBatimentsDTO() {
        return batimentRepository.findAll()
                                 .stream()
                                 .map(BatimentMapper::toDTO)
                                 .collect(Collectors.toList());
}
    public BatimentDTO saveBatimentDTO(BatimentDTO dto) {
        Batiment entity = BatimentMapper.toEntity(dto);
        Batiment saved = batimentRepository.save(entity);
        return BatimentMapper.toDTO(saved);
    }
    
    @Transactional
    public Optional<BatimentDTO> updateBatiment(Long id, BatimentDTO dtoDetails) {
        return batimentRepository.findById(id)
            .map(existingBatiment -> {
                
                if (dtoDetails.getAdresse() != null) 
                    existingBatiment.setAdresse(dtoDetails.getAdresse());
                if (dtoDetails.getVille() != null) 
                    existingBatiment.setVille(dtoDetails.getVille());
                
                
                Batiment updatedBatiment = batimentRepository.save(existingBatiment);
                return BatimentMapper.toDTO(updatedBatiment);
            });
    }
    
    @Transactional
    public boolean deleteBatiment(Long id) {
        if (batimentRepository.existsById(id)) {
            batimentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}