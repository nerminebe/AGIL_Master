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
@Table(name = "commercials")
public class Commercial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;
    private String prenom;
    private String type;
}
