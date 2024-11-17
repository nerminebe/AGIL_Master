package org.example.controllers.external;

import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.example.dtos.views.ProduitDto;
import org.example.dtos.views.ProduitQteDto;
import org.example.payload.requests.CreateProductRequest;
import org.example.payload.requests.UpdateProductRequest;
import org.example.services.ProduitService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/produits")
@RequiredArgsConstructor
public class ProduitController {

    private final ProduitService produitService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    public List<ProduitDto> getAllProducts() {
        return produitService.getAllProducts();
    }

    @GetMapping("/station/{stationId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    public List<ProduitQteDto> getAllProductsByStationId(@ApiParam(name = "stationId") @PathVariable("stationId") Long stationId) {
        return produitService.getAllProductsByStationId(stationId);
    }

    @GetMapping("/{produitId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    public ProduitDto getProductById(@ApiParam(name = "produitId") @PathVariable("produitId") Long produitId) {
        return produitService.getById(produitId);
    }


}
