package org.example.services;

import org.example.dtos.views.ProduitDto;
import org.example.dtos.views.ProduitQteDto;
import org.example.payload.requests.CreateProductRequest;
import org.example.payload.requests.UpdateProductRequest;

import java.util.List;

public interface ProduitService {

    List<ProduitDto> getAllProducts();

    List<ProduitQteDto> getAllProductsByStationId(Long StationId);

    ProduitDto create(CreateProductRequest createProductRequest);

    ProduitDto update(Long produitId, UpdateProductRequest updateProductRequest);

    ProduitDto getById(Long produitId);

    void ajouterProductToStationId(Long stationId, Long productId, Long qte);

    void setProductQteDansStationId(Long stationId, Long productId, Long qte);

}
