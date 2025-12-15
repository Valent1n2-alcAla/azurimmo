package bts.sio.azurimmo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Loyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate datePaiement;
    private Double montantPaye;

    // RELATION : Plusieurs Loyers sont liés au même Contrat (1, 1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contrat_id", nullable = false)
    private Contrat contrat;
}