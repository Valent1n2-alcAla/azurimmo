package bts.sio.azurimmo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Getter
@Setter
@Table(name = "contrat")
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    @Column(name = "montant_brut")
    private Double montantBrut;

    @Column(name = "montant_charge")
    private Double montantCharge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appartement_id", nullable = false)
    @JsonIgnoreProperties("contrats") 
    private Appartement appartement;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "locataire_id", nullable = false)
    @JsonIgnoreProperties("contrats")
    private Locataire locataire;

    @OneToMany(mappedBy = "contrat", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("contrat")
    private List<Loyer> loyers;
}