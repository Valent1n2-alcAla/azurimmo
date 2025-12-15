package bts.sio.azurimmo.model.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class ContratDTO {
    private Long id;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Double montantBrut;
    private Double montantCharge;
    
    // Changement : on utilise l'objet Locataire et Appartement (sous forme de DTO)
    private LocataireDTO locataire;   
    private AppartementDTO appartement; 
}