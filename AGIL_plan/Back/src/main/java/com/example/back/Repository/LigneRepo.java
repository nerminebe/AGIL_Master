package com.example.back.repositories;

import com.example.back.entity.Ligne_de_commande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneRepo  extends JpaRepository<Ligne_de_commande, Long> {

    List<Ligne_de_commande> findByProduitId(int produitId);

    // Trouver les lignes de commande par l'ID de la commande
    List<Ligne_de_commande> findByCommandeId(int commandeId);
}
