package org.example.dtos.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LigneCommandeDTO {
    private Long id;
    private ProduitDto produit;
    private int quantite;
    private double prixTotal;
}
