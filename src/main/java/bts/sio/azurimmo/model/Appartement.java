package bts.sio.azurimmo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "appartement")
public class Appartement {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "surface")
    private Double surface;

    @Column(name = "nb_pieces")
    private Integer nombrePieces;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "appartement", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("appartement")
    private List<Contrat> contrats;

    @ManyToOne
    @JoinColumn(name = "batiment_id")
    @JsonIgnoreProperties("appartements") // Empêche de remonter vers le bâtiment (évite le null ou la boucle)
    private Batiment batiment;
}