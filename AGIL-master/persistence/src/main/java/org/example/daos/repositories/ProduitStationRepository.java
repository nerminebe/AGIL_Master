package org.example.daos.repositories;

import org.example.daos.entities.ProduitStation;
import org.example.utils.ProduitQte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProduitStationRepository extends JpaRepository<ProduitStation, Long> {


    @Query("SELECT new org.example.utils.ProduitQte(ps.produit, ps.qte) " +
            "FROM ProduitStation ps WHERE ps.station.id = :stationId")
    List<ProduitQte> findProduitsAndQteByStationId(@Param("stationId") Long stationId);

    Optional<ProduitStation> findProduitStationByProduit_IdAndStation_Id(Long produitId, Long stationId);
}
