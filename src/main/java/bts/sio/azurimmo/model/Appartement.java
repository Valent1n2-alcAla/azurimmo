package bts.sio.azurimmo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

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

	// Caractéristique: surface
	@Column(name = "surface")
	private Double surface;

	
	@Column(name = "nb_pieces")
	private Integer nombrePieces;

	@Column(name = "description")
	private String description;
    
	@ManyToOne
	@JoinColumn(name = "batiment_id")
	private Batiment batiment;

}