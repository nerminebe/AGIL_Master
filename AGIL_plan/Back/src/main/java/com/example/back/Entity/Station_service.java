package com.example.back.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "station_services")
public class Station_service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String adresse;
    private String ville;

    @OneToOne(mappedBy = "station_service")
    private Gerant gerant;

    @OneToMany(mappedBy = "station_service")
    private List<Gerant> gerants;
}
