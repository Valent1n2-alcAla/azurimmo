package bts.sio.azurimmo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Double montantBrut;
    private Double montantCharge;

    // RELATION : Plusieurs Contrats peuvent lier le même Appartement (1, 1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appartement_id", nullable = false)
    private Appartement appartement;

    // RELATION : Plusieurs Contrats peuvent être signés par le même Locataire (1, 1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "locataire_id", nullable = false)
    private Locataire locataire;

    // RELATION : Un Contrat génère plusieurs Loyers (1, *)
    @OneToMany(mappedBy = "contrat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Loyer> loyers;
}