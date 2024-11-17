package com.example.back.repositories;

import com.example.back.entity.Gerant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GerantRepo extends JpaRepository<Gerant, Long> {

    List<Gerant> findByStation_serviceId(Long stationServiceId);
    int countByStation_serviceId(Long stationServiceId);
}
