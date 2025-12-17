package bts.sio.azurimmo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List; // Nécessaire pour la liste de Contrats

@Entity
@Getter
@Setter
public class Locataire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String telephone;
    private String mail;
    private LocalDate dateNaissance;

    @OneToMany(mappedBy = "locataire", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contrat> contrats; 
}