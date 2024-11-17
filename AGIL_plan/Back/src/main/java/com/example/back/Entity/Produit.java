package com.example.back.entity;

import lombok.*;
import org.springframework.data.relational.core.mapping.Table;
import jakarta.persistence.*;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "produits")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;
    private String typeProduit;
    private float prix;
    private boolean disponibilite;
    private int quantite;

}