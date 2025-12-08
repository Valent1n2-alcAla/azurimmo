package bts.sio.azurimmo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import bts.sio.azurimmo.model.dto.BatimentDTO;
import bts.sio.azurimmo.repository.BatimentRepository;
import bts.sio.azurimmo.model.mapper.BatimentMapper;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 

@Service
public class BatimentService {
    
    // 1. Injection de l'INSTANCE du Repository
    @Autowired 
    private BatimentRepository batimentRepository; 

    public Optional<BatimentDTO> getBatimentDTO(Long id) {
        
        // 2. Appel de la méthode sur l'INSTANCE injectée
        return batimentRepository.findById(id) 
                                 .map(BatimentMapper::toDTO);
    }
    
    public List<BatimentDTO> getBatimentsDTO() {
        return batimentRepository.findAll()
                                 .stream()
                                 .map(BatimentMapper::toDTO)
                                 .collect(Collectors.toList());
}
}