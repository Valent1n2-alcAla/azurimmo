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

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appartement_id", nullable = false)
    private Appartement appartement;

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "locataire_id", nullable = false)
    private Locataire locataire;

    
    @OneToMany(mappedBy = "contrat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Loyer> loyers;
}