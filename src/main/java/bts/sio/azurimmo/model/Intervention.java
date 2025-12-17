package bts.sio.azurimmo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Intervention {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private LocalDate datePrevue;
    private LocalDate dateRealise;
    private String description;

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appartement_id", nullable = false)
    private Appartement appartement;
}