package com.example.back.repositories;

import com.example.back.entity.Intervenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntervenantRepo extends JpaRepository<Intervenant, Long> {
}
