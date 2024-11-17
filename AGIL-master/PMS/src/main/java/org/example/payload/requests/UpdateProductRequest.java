package org.example.payload.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.enums.TypeProduit;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateProductRequest {

    private String nom;
    private TypeProduit typeProduit;
    private double prixUnitaire;

}
