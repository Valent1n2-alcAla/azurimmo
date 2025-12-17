package bts.sio.azurimmo.model.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class InterventionDTO {
    private Long id;
    private String type;
    private LocalDate datePrevue;
    private LocalDate dateRealise;
    private String description;
    
  
    private AppartementDTO appartement;
}