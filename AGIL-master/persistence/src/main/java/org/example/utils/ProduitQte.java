package org.example.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.daos.entities.Produit;

@NoArgsConstructor
@Getter
@Setter
public class ProduitQte {
    private Produit produit;
    private Long qte;

    public ProduitQte(Produit produit, Long qte) {
        this.produit = produit;
        this.qte = qte;
    }

}
