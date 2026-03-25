package bts.sio.azurimmo.model.dto;

import lombok.Getter;
import java.util.List;
import lombok.Setter;

@Getter
@Setter
public class AppartementDTO {
     private Long id; 

     private Integer numero;
	 private String description;
	 private Double surface;
	 private Integer nombrePieces;
	 private List<ContratDTO> contrats;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}