package com.example.back.entity;

import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "intervenants")
public class Intervenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;
    private String prenom;
    private String typeDirection;
}