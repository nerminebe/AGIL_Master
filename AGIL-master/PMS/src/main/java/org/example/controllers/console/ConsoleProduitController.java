package org.example.controllers.console;

import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.example.dtos.views.ProduitDto;
import org.example.payload.requests.CreateProductRequest;
import org.example.payload.requests.UpdateProductRequest;
import org.example.services.ProduitService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("console/api/v1/produits")
@RequiredArgsConstructor
public class ConsoleProduitController {

    private final ProduitService produitService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    public ProduitDto createProduct(@RequestBody CreateProductRequest createProductRequest) {
        return produitService.create(createProductRequest);
    }

    @PutMapping("/{produitId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    public ProduitDto updateProduct(@ApiParam(name = "produitId") @PathVariable("produitId") Long produitId, @RequestBody UpdateProductRequest updateProductRequest) {
        return produitService.update(produitId, updateProductRequest);
    }


}
