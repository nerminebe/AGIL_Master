package org.example.dtos.views;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProduitQteDto {

    private ProduitDto produit;
    private Long qte;

}
