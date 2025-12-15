package bts.sio.azurimmo.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppartementDTO {
     private Long id; // <-- ATTENTION : L'ID DOIT ÊTRE UN LONG

     private Integer numero;
	 private String description;
	 private Double surface;

    // --- Ajout manuel pour garantir la compilation (au cas où Lombok échoue) ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}