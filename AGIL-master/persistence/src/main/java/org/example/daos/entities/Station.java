package org.example.daos.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.enums.TypeStation;
import org.example.utils.Auditable;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "AGIL_Station")
public class Station extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String adresse;

    @Column(unique = true)
    private String matricule;

    private String telephone;

    private TypeStation typeStation;

    @ManyToOne(fetch = FetchType.EAGER)
    private User gerant;

}
