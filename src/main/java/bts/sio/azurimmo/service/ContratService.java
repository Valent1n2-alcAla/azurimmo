package bts.sio.azurimmo.service;

import bts.sio.azurimmo.model.dto.ContratDTO;
import bts.sio.azurimmo.model.Appartement;
import bts.sio.azurimmo.model.Contrat;
import bts.sio.azurimmo.model.Locataire;
import bts.sio.azurimmo.model.mapper.ContratMapper;
import bts.sio.azurimmo.repository.AppartementRepository;
import bts.sio.azurimmo.repository.ContratRepository;
import bts.sio.azurimmo.repository.LocataireRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContratService {

    private final ContratRepository contratRepository;

    private final AppartementRepository appartementRepository;
    private final LocataireRepository locataireRepository;


    public ContratService(
        ContratRepository contratRepository,
        AppartementRepository appartementRepository,
        LocataireRepository locataireRepository) {
        this.contratRepository = contratRepository;
        this.appartementRepository = appartementRepository;
        this.locataireRepository = locataireRepository;
    }

    public List<ContratDTO> getAllContrats() {
        return contratRepository.findAll().stream()
                .map(ContratMapper::toDTO)
                .collect(Collectors.toList());
    }

    
    public Optional<ContratDTO> getContratById(Long id) {
        return contratRepository.findById(id)
                .map(ContratMapper::toDTO);
    }

    @Transactional
    public ContratDTO createContrat(ContratDTO contratDTO) {
        Contrat contrat = ContratMapper.toEntity(contratDTO);

       
        if (contratDTO.getAppartement() != null && contratDTO.getAppartement().getId() != null) {
            Appartement appartement = appartementRepository.findById(contratDTO.getAppartement().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Appartement non trouvé avec l'ID: " + contratDTO.getAppartement().getId()));
            contrat.setAppartement(appartement);
        } else {
             throw new IllegalArgumentException("L'ID de l'Appartement est requis pour la création du contrat.");
        }

      
        if (contratDTO.getLocataire() != null && contratDTO.getLocataire().getId() != null) {
            Locataire locataire = locataireRepository.findById(contratDTO.getLocataire().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Locataire non trouvé avec l'ID: " + contratDTO.getLocataire().getId()));
            contrat.setLocataire(locataire);
        } else {
             throw new IllegalArgumentException("L'ID du Locataire est requis pour la création du contrat.");
        }

       
        Contrat savedContrat = contratRepository.save(contrat);
        return ContratMapper.toDTO(savedContrat);
    }

    
    @Transactional
    public Optional<ContratDTO> updateContrat(Long id, ContratDTO contratDTO) {
        return contratRepository.findById(id)
            .map(existingContrat -> {
                existingContrat.setDateDebut(contratDTO.getDateDebut());
                existingContrat.setDateFin(contratDTO.getDateFin());
                existingContrat.setMontantBrut(contratDTO.getMontantBrut());
                existingContrat.setMontantCharge(contratDTO.getMontantCharge());

               
                if (contratDTO.getAppartement() != null && contratDTO.getAppartement().getId() != null) {
                    Appartement appartement = appartementRepository.findById(contratDTO.getAppartement().getId())
                        .orElseThrow(() -> new IllegalArgumentException("Appartement non trouvé avec l'ID: " + contratDTO.getAppartement().getId()));
                    existingContrat.setAppartement(appartement);
                } else {
                    throw new IllegalArgumentException("L'ID de l'Appartement est requis pour la mise à jour du contrat.");
                }

                if (contratDTO.getLocataire() != null && contratDTO.getLocataire().getId() != null) {
                    Locataire locataire = locataireRepository.findById(contratDTO.getLocataire().getId())
                        .orElseThrow(() -> new IllegalArgumentException("Locataire non trouvé avec l'ID: " + contratDTO.getLocataire().getId()));
                    existingContrat.setLocataire(locataire);
                } else {
                    throw new IllegalArgumentException("L'ID du Locataire est requis pour la mise à jour du contrat.");
                }

                Contrat updatedContrat = contratRepository.save(existingContrat);
                return ContratMapper.toDTO(updatedContrat);
            });
    }

    
    @Transactional
    public boolean deleteContrat(Long id) {
        if (contratRepository.existsById(id)) {
            contratRepository.deleteById(id);
            return true;
        }
        return false;
    }
}