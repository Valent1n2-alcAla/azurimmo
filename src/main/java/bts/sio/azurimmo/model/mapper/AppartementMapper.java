package bts.sio.azurimmo.model.mapper;

import bts.sio.azurimmo.model.Appartement;
import bts.sio.azurimmo.model.dto.AppartementDTO;
import java.util.stream.Collectors;

public class AppartementMapper {
    
    public static AppartementDTO toDTO(Appartement a) {
        if (a == null) return null;

        AppartementDTO dto = new AppartementDTO();
        dto.setId(a.getId()); // On ajoute l'ID c'est toujours plus sûr
        dto.setNumero(a.getNumero());
        dto.setDescription(a.getDescription());
        dto.setSurface(a.getSurface());
        dto.setNombrePieces(a.getNombrePieces());

        // --- CORRECTION : Ajout des contrats pour avoir le prix et le locataire ---
        if (a.getContrats() != null) {
            dto.setContrats(a.getContrats().stream()
                .map(ContratMapper::toDTO) // On transforme chaque contrat en DTO
                .collect(Collectors.toList()));
        }
        
        return dto;
    }

    public static Appartement toEntity(AppartementDTO dto) {
        if (dto == null) return null;

        Appartement a = new Appartement();
        a.setId(dto.getId());
        a.setNumero(dto.getNumero());
        a.setDescription(dto.getDescription());
        a.setSurface(dto.getSurface());
        a.setNombrePieces(dto.getNombrePieces()); // On le rajoute aussi ici par cohérence
        return a;
    }
}