package bts.sio.azurimmo.repository;

import org.springframework.stereotype.Repository;
import bts.sio.azurimmo.model.Appartement; // Importez l'entité Appartement

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
// Le Repository doit étendre JpaRepository<Entité, TypeCléPrimaire>
public interface AppartementRepository extends JpaRepository<Appartement, Long> {
    
    // Ajout de la méthode nécessaire pour le contrôleur (relation manuelle)
    List<Appartement> findByBatimentId(Long batimentId);
    List<Appartement> findByBatiment_Ville(String ville);
    List<Appartement> findByBatiment_Id(Long id);
    List<Appartement> findBySurfaceGreaterThan(Double surface);
}