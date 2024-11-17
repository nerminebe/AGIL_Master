package com.example.back.service;

import com.example.back.entity.Produit;
import com.example.back.repositories.ProduitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepo produitRepository;

    public Produit createProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    public Produit getProduitById(int id) {
        return produitRepository.findById(id).orElse(null);
    }

    public Produit updateProduit(int id, Produit updatedProduit) {
        Optional<Produit> optionalProduit = produitRepository.findById(id);
        if (optionalProduit.isPresent()) {
            Produit existingProduit = optionalProduit.get();
            existingProduit.setNom(updatedProduit.getNom());
            existingProduit.setTypeProduit(updatedProduit.getTypeProduit());
            existingProduit.setPrix(updatedProduit.getPrix());
            existingProduit.setQuantite(updatedProduit.getQuantite());
            existingProduit.setDisponibilite(updatedProduit.isDisponibilite());
            return produitRepository.save(existingProduit);
        } else {
            return null;
        }
    }

    public void deleteProduit(int id) {
        produitRepository.deleteById(id);
    }

    public boolean checkStockAvailability(int produitId, int quantite) {
        Optional<Produit> produit = produitRepository.findById(produitId);
        return produit.isPresent() && produit.get().getQuantite() >= quantite;
    }

    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }
}