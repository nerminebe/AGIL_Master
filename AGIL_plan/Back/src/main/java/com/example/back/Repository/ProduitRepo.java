package com.example.back.repositories;

import com.example.back.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepo extends JpaRepository<Produit, Long> {
}
