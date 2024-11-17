package com.example.back.entity;

import lombok.*;
import jakarta.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "gerants")
public class Gerant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matricule;

    private String nom;
    private String prenom;
    private int telephone;
    private String depotLivreur;

    @ManyToOne
    @JoinColumn(name = "commercial_id", referencedColumnName = "id")
    private Commercial commercial;

    @OneToMany(mappedBy = "gerant")
    private List<Commande> commandes;

    @OneToMany(mappedBy = "gerant")
    private List<Reclamation> reclamations;
}
