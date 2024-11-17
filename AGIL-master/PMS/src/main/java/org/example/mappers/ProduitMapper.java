package org.example.mappers;

import lombok.RequiredArgsConstructor;
import org.example.daos.entities.Produit;
import org.example.dtos.views.ProduitDto;
import org.example.dtos.views.ProduitQteDto;
import org.example.payload.requests.CreateProductRequest;
import org.example.payload.requests.UpdateProductRequest;
import org.example.utils.ProduitQte;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProduitMapper {

    private final ModelMapper modelMapper;


    public ProduitDto toProduitDto(Produit produit) {
        return modelMapper.map(produit, ProduitDto.class);
    }

    public ProduitQteDto toProduitQteDto(ProduitQte produitQte) {
        return new ProduitQteDto(toProduitDto(produitQte.getProduit()), produitQte.getQte());
    }

    public Produit toEntity(CreateProductRequest createProductRequest) {
        return modelMapper.map(createProductRequest, Produit.class);
    }

    public Produit toEntity(UpdateProductRequest updateProductRequest) {
        return modelMapper.map(updateProductRequest, Produit.class);
    }

}
