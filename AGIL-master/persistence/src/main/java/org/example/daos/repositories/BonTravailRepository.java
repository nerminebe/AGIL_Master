package org.example.daos.repositories;

import org.example.daos.entities.BonTravail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BonTravailRepository extends JpaRepository<BonTravail, Long> {

    List<BonTravail> findByTechnicien_Id(Long technicienId);

    List<BonTravail> findByReclamation_Id(Long reclamationId);

}
