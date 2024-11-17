package org.example.dtos.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.enums.TypeProduit;
import org.example.utils.Auditable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProduitDto extends Auditable {
    private Long id;
    private String nom;
    private TypeProduit typeProduit;
    private double prixUnitaire;
}
