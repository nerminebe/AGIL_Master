package org.example.controllers.console;

import lombok.RequiredArgsConstructor;
import org.example.payload.requests.StationProduitQteRequest;
import org.example.services.ProduitService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("console/api/v1/produitstation")
@RequiredArgsConstructor
public class ConsoleProduitStationController {

    private final ProduitService produitService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    public void ajouterProductToStationId(@RequestBody StationProduitQteRequest stationProduitQteRequest) {
        produitService.ajouterProductToStationId(stationProduitQteRequest.getStationId(), stationProduitQteRequest.getProductId(), stationProduitQteRequest.getQte());
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    public void setProductQteDansStationId(@RequestBody StationProduitQteRequest stationProduitQteRequest) {
        produitService.setProductQteDansStationId(stationProduitQteRequest.getStationId(), stationProduitQteRequest.getProductId(), stationProduitQteRequest.getQte());
    }

}
