package bts.sio.azurimmo.service;

import bts.sio.azurimmo.model.Locataire;
import bts.sio.azurimmo.model.dto.LocataireDTO;
import bts.sio.azurimmo.model.mapper.LocataireMapper;
import bts.sio.azurimmo.repository.LocataireRepository;
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
        // Convertir DTO en Entité (sans se soucier des relations pour l'instant)
        Locataire entity = LocataireMapper.toEntity(dto);
        Locataire savedEntity = locataireRepository.save(entity);
        return LocataireMapper.toDTO(savedEntity);
    }
}