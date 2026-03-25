package bts.sio.azurimmo.service;

import java.util.List;
import java.util.Optional; // Important pour findById

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bts.sio.azurimmo.model.Appartement;
import bts.sio.azurimmo.repository.AppartementRepository;

@Service
public class AppartementService {
	
    @Autowired
    private AppartementRepository appartementRepository;

    // Récupérer tous les appartements
    public List<Appartement> getAppartements() {
        return appartementRepository.findAll();
    }

    // RÉCUPÉRER PAR ID (C'est cette méthode qui manquait pour ton Controller)
    public Optional<Appartement> getAppartement(Long id) {
        return appartementRepository.findById(id);
    }

    public Appartement saveAppartement(Appartement appartement) {
        return appartementRepository.save(appartement);
    }
	
    public List<Appartement> findByVille(String ville) {
        return appartementRepository.findByBatiment_Ville(ville);
    }

    public List<Appartement> getAppartementsParBatiment(long id) {
        return appartementRepository.findByBatiment_Id(id);
    }
	
    public List<Appartement> getSurfaceGreaterThan(Double surface) {
        return appartementRepository.findBySurfaceGreaterThan(surface);
    }
}