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

	// Caractéristique: numero (par rapport au batiment)
	@Column(name = "numero")
	private Integer numero;

	// Caractéristique: surface
	@Column(name = "surface")
	private Double surface;

	// Caractéristique: nombre de pièces principales
	@Column(name = "nb_pieces")
	private Integer nombrePieces;

	// Caractéristique: description
	@Column(name = "description")
	private String description;
    
	@ManyToOne
	@JoinColumn(name = "batiment_id")
	private Batiment batiment;

}