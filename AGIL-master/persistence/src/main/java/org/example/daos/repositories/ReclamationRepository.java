package org.example.daos.repositories;

import org.example.daos.entities.Reclamation;
import org.example.enums.TypeReclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation, Long> {

    List<Reclamation> findByGerant_Id(Long gerantId);


}
