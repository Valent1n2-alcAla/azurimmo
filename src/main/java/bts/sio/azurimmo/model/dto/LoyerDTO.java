package bts.sio.azurimmo.model.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class LoyerDTO {
    private Long id;
    private LocalDate datePaiement;
    private Double montantPaye;
    
    
    private ContratDTO contrat; 
}