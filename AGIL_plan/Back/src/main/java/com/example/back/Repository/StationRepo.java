package com.example.back.repositories;

import com.example.back.entity.Station_service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepo extends JpaRepository<Station_service, Long> {
}
