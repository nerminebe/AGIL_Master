package com.example.back.repositories;

import com.example.back.entity.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReclamationRepo extends JpaRepository<Reclamation, Long> {
    List<Reclamation> findByGerantId(int gerantId);


}
