package bts.sio.azurimmo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "batiment")
public class Batiment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="adresse")
    private String adresse;

    @Column(name="ville")
    private String ville;

    @OneToMany(mappedBy = "batiment", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference // Autorise l'affichage des appartements dans le JSON du bâtiment
    private List<Appartement> appartements = new ArrayList<>();
}