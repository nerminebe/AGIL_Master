package org.example.mappers;

import org.example.daos.entities.LigneCommande;
import org.example.dtos.views.LigneCommandeDTO;
import org.example.dtos.views.ProduitDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LigneCommandeMapper {

    private final ModelMapper modelMapper;

    public LigneCommandeMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public LigneCommandeDTO toDTO(LigneCommande ligneCommande) {
        LigneCommandeDTO ligneCommandeDTO = modelMapper.map(ligneCommande, LigneCommandeDTO.class);
        ligneCommandeDTO.setProduit(modelMapper.map(ligneCommande.getProduit(), ProduitDto.class));
        return ligneCommandeDTO;
    }

}
