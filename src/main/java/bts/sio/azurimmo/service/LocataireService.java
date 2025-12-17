package bts.sio.azurimmo.service;

import bts.sio.azurimmo.model.Locataire;
import bts.sio.azurimmo.model.dto.LocataireDTO;
import bts.sio.azurimmo.model.mapper.LocataireMapper;
import bts.sio.azurimmo.repository.LocataireRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LocataireService {
    @Autowired
    private LocataireRepository locataireRepository;

    public List<LocataireDTO> findAll() {
        return locataireRepository.findAll().stream()
                .map(LocataireMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<LocataireDTO> findById(Long id) {
        return locataireRepository.findById(id)
                .map(LocataireMapper::toDTO);
    }

    public LocataireDTO save(LocataireDTO dto) {
        Locataire entity = LocataireMapper.toEntity(dto);
        Locataire savedEntity = locataireRepository.save(entity);
        return LocataireMapper.toDTO(savedEntity);
    }
    
    @Transactional
    public Optional<LocataireDTO> updateLocataire(Long id, LocataireDTO dtoDetails) {
        return locataireRepository.findById(id)
            .map(existingLocataire -> {
                
                if (dtoDetails.getNom() != null) 
                    existingLocataire.setNom(dtoDetails.getNom());
                if (dtoDetails.getPrenom() != null) 
                    existingLocataire.setPrenom(dtoDetails.getPrenom());
                
                
                Locataire updatedLocataire = locataireRepository.save(existingLocataire);
                return LocataireMapper.toDTO(updatedLocataire);
            });
    }
    
    @Transactional
    public boolean deleteLocataire(Long id) {
        if (locataireRepository.existsById(id)) {
            locataireRepository.deleteById(id);
            return true;
        }
        return false;
    }
}