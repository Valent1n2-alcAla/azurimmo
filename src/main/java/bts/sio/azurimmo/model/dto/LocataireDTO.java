package bts.sio.azurimmo.model.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class LocataireDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String telephone;
    private String mail;
    private LocalDate dateNaissance;
    
    public Long getId() {
    	return id;
    }
    
    public long setId(Long id) {
    	return this.id=id;
    }
}