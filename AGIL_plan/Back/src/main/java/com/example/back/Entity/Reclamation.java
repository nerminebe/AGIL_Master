package com.example.back.entity;


import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.persistence.*;
import java.util.Date;


@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reclamations")
public class Reclamation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String type;
    private String description;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "gerant_id", referencedColumnName = "matricule")
    private Gerant gerant;

    @ManyToOne
    private Intervenant intervenant;

   // @ManyToOne
  //  @JoinColumn(name = "utilisateur_id", referencedColumnName = "id")
   // private User responsable;
}