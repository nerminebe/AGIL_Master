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
@Table(name = "lignes_de_commande")
public class Ligne_de_commande {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        private String reference;
        private int quantiteCommandee;
        private int quantiteLivree;

        @ManyToOne
        @JoinColumn(name = "commande_id", referencedColumnName = "id")
        private Commande commande;

        @ManyToOne
        @JoinColumn(name = "produit_id", referencedColumnName = "id")
        private Produit produit;

    }
