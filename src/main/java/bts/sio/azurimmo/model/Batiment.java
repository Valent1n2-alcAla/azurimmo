package bts.sio.azurimmo.model;

import jakarta.persistence.CascadeType; // NOUVEL IMPORT
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany; // NOUVEL IMPORT
import jakarta.persistence.Table;
import lombok.Data;
import java.util.List; // NOUVEL IMPORT

@Data
@Entity
@Table(name = "batiment")
public class Batiment {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

	 @Column(name="adresse")
	 private String adresse;

	 @Column(name="ville")
	 private String ville;
	 
	 
	 // -------------------------------------------------------------------
	 // AJOUT DE LA RELATION ONE-TO-MANY (Résout l'erreur getAppartements())
	 // -------------------------------------------------------------------
	 
	 @OneToMany(mappedBy = "batiment", cascade = CascadeType.ALL, orphanRemoval = true)
     // Lombok (@Data) va générer automatiquement le getAppartements() nécessaire au Mapper
	 private List<Appartement> appartements; 
}