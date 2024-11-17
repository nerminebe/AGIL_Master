package org.example.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.daos.entities.Produit;
import org.example.daos.entities.ProduitStation;
import org.example.daos.entities.Station;
import org.example.daos.repositories.ProduitRepository;
import org.example.daos.repositories.ProduitStationRepository;
import org.example.daos.repositories.StationRepository;
import org.example.dtos.views.ProduitDto;
import org.example.dtos.views.ProduitQteDto;
import org.example.exceptions.DataNotFoundException;
import org.example.mappers.ProduitMapper;
import org.example.payload.requests.CreateProductRequest;
import org.example.payload.requests.UpdateProductRequest;
import org.example.services.ProduitService;
import org.example.utils.ProduitQte;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProduitServiceImpl implements ProduitService {

    private final ProduitRepository produitRepository;
    private final ProduitStationRepository produitStationRepository;
    private final ProduitMapper produitMapper;
    private final StationRepository stationRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ProduitDto> getAllProducts() {
        return produitRepository.findAll().stream().map(produitMapper::toProduitDto).toList();
    }

    @Override
    public List<ProduitQteDto> getAllProductsByStationId(Long stationId) {
        Station station = stationRepository.findById(stationId).orElseThrow(() -> new DataNotFoundException("station not found"));
        List<ProduitQte> produits = produitStationRepository.findProduitsAndQteByStationId(station.getId());
        return produits.stream().map(produitMapper::toProduitQteDto).toList();
    }

    @Override
    public ProduitDto create(CreateProductRequest createProductRequest) {
        Produit produit = produitMapper.toEntity(createProductRequest);
        return produitMapper.toProduitDto(produitRepository.save(produit));
    }

    @Override
    public ProduitDto update(Long produitId, UpdateProductRequest updateProductRequest) {
        Produit produit = produitRepository.findById(produitId).orElseThrow(() -> new DataNotFoundException("Product not found"));
        modelMapper.map(updateProductRequest, produit);
        return produitMapper.toProduitDto(produitRepository.save(produit));
    }

    @Override
    public ProduitDto getById(Long produitId) {
        return produitMapper.toProduitDto(produitRepository.findById(produitId).orElseThrow(() -> new DataNotFoundException("Product not found")));
    }

    @Override
    public void ajouterProductToStationId(Long stationId, Long productId, Long qte) {
        ProduitStation produitStation = produitStationRepository.findProduitStationByProduit_IdAndStation_Id(productId, stationId).orElse(null);
        if (produitStation == null) {
            Produit produit = produitRepository.findById(productId).orElseThrow(() -> new DataNotFoundException("Produit inexistant"));
            Station station = stationRepository.findById(stationId).orElseThrow(() -> new DataNotFoundException("Station inexistante"));
            produitStation = new ProduitStation(null, produit, station, qte);
        } else {
            produitStation.setQte(produitStation.getQte() + qte);
        }
        produitStationRepository.save(produitStation);
    }

    @Override
    public void setProductQteDansStationId(Long stationId, Long productId, Long qte) {
        ProduitStation produitStation = produitStationRepository.findProduitStationByProduit_IdAndStation_Id(productId, stationId)
                .orElseThrow(() -> new DataNotFoundException("Product not exists in this station !"));
        produitStation.setQte(qte);
        produitStationRepository.save(produitStation);
    }
}
