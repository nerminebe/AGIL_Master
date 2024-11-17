package com.example.back.service;

import com.example.back.entity.Ligne_de_commande;
import com.example.back.entity.Produit;
import com.example.back.repositories.LigneRepo;
import com.example.back.repositories.ProduitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LigneService {



        @Autowired
        private LigneRepo ligneDeCommandeRepository;

        @Autowired
        private ProduitRepo produitRepository;

        public Ligne_de_commande createLigneDeCommande(Ligne_de_commande ligneDeCommande) {
            Optional<Produit> produit = produitRepository.findById(ligneDeCommande.getProduit().getId());
            if (produit.isPresent() && produit.get().getQuantite() >= ligneDeCommande.getQuantiteCommandee()) {
                // Déduire la quantité du stock produit
                produit.get().setQuantite(produit.get().getQuantite() - ligneDeCommande.getQuantiteCommandee());
                produitRepository.save(produit.get());
                return ligneDeCommandeRepository.save(ligneDeCommande);
            } else {
                // Gérer le cas où la quantité commandée dépasse le stock disponible
                return null;
            }
        }

        public Ligne_de_commande getLigneDeCommandeById(int id) {
            return ligneDeCommandeRepository.findById(id).orElse(null);
        }
        public Ligne_de_commande updateLigneDeCommande(int id, Ligne_de_commande updatedLigneDeCommande) {
            Optional<Ligne_de_commande> optionalLigneDeCommande = ligneDeCommandeRepository.findById(id);
            if (optionalLigneDeCommande.isPresent()) {
                Ligne_de_commande existingLigneDeCommande = optionalLigneDeCommande.get();
                existingLigneDeCommande.setQuantiteCommandee(updatedLigneDeCommande.getQuantiteCommandee());
                existingLigneDeCommande.setProduit(updatedLigneDeCommande.getProduit());
                return ligneDeCommandeRepository.save(existingLigneDeCommande);
            } else {
                return null;
            }
        }

        public void deleteLigneDeCommande(int id) {
            ligneDeCommandeRepository.deleteById(id);
        }

        public List<Ligne_de_commande> getLignesDeCommandeByCommandeId(int commandeId) {
            return ligneDeCommandeRepository.findByCommandeId(commandeId);
        }

        public List<Ligne_de_commande> getLignesDeCommandeByProduitId(int produitId) {
            return ligneDeCommandeRepository.findByProduitId(produitId);
        }

        public List<Ligne_de_commande> getAllLignesDeCommande() {
            return ligneDeCommandeRepository.findAll();
        }

    public boolean checkStockAvailability(int produitId, int quantite) {
        Optional<Produit> produit = produitRepository.findById(produitId);
        return produit.isPresent() && produit.get().getQuantite() >= quantite;
    }

}