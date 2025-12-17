package bts.sio.azurimmo.service;

import bts.sio.azurimmo.model.dto.InterventionDTO;
import bts.sio.azurimmo.model.Appartement;
import bts.sio.azurimmo.model.Intervention;
import bts.sio.azurimmo.model.mapper.InterventionMapper;
import bts.sio.azurimmo.repository.AppartementRepository;
import bts.sio.azurimmo.repository.InterventionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InterventionService {

    private final InterventionRepository interventionRepository;
    private final AppartementRepository appartementRepository;

    @Autowired
    public InterventionService(InterventionRepository interventionRepository, 
                               AppartementRepository appartementRepository) {
        this.interventionRepository = interventionRepository;
        this.appartementRepository = appartementRepository;
    }

    @Transactional
    public InterventionDTO createIntervention(InterventionDTO interventionDTO) {
        
        Long appartementId = interventionDTO.getAppartement().getId();
        
        
        Appartement appartement = appartementRepository.findById(appartementId)
                .orElseThrow(() -> new IllegalArgumentException("Appartement non trouvé avec l'ID: " + appartementId));

       
        Intervention intervention = InterventionMapper.toEntity(interventionDTO);
        intervention.setAppartement(appartement);

        
        Intervention savedIntervention = interventionRepository.save(intervention);
        return InterventionMapper.toDTO(savedIntervention);
    }

    
    public List<InterventionDTO> getAllInterventions() {
        return interventionRepository.findAll().stream()
                .map(InterventionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<InterventionDTO> getInterventionById(Long id) {
        return interventionRepository.findById(id)
                .map(InterventionMapper::toDTO);
    }

    
    @Transactional
    public Optional<InterventionDTO> updateIntervention(Long id, InterventionDTO details) {
        return interventionRepository.findById(id)
                .map(existingIntervention -> {
                    
                    
                    if (details.getAppartement() != null && details.getAppartement().getId() != null) {
                        Long newAppartementId = details.getAppartement().getId();
                        
                       
                        if (!newAppartementId.equals(existingIntervention.getAppartement().getId())) {
                            Appartement newAppartement = appartementRepository.findById(newAppartementId)
                                    .orElseThrow(() -> new IllegalArgumentException("Appartement non trouvé pour la mise à jour avec l'ID: " + newAppartementId));
                            existingIntervention.setAppartement(newAppartement);
                        }
                    } else {
                        throw new IllegalArgumentException("L'ID de l'Appartement est requis pour la mise à jour de l'intervention.");
                    }
                    
                    
                    if (details.getType() != null) {
                        existingIntervention.setType(details.getType());
                    }
                    if (details.getDatePrevue() != null) {
                        existingIntervention.setDatePrevue(details.getDatePrevue());
                    }
                    if (details.getDateRealise() != null) {
                        existingIntervention.setDateRealise(details.getDateRealise());
                    }
                    if (details.getDescription() != null) {
                        existingIntervention.setDescription(details.getDescription());
                    }

                    Intervention updatedIntervention = interventionRepository.save(existingIntervention);
                    return InterventionMapper.toDTO(updatedIntervention);
                });
    }

    @Transactional
    public boolean deleteIntervention(Long id) {
        if (interventionRepository.existsById(id)) {
            interventionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}