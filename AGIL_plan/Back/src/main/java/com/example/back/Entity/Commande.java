package com.example.back.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "commandes")
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private Etat_de_commande etat;

    private Date date;
    private float montant;
    private String nomDuChauffeur;
    private String tel;
    private String referenceCommande;

    @ManyToOne
    @JoinColumn(name = "gerant_id", referencedColumnName = "matricule")
    private Gerant gerant;

    @OneToMany(mappedBy = "commande")
    private List<Ligne_de_commande> lignesDeCommande;

}
