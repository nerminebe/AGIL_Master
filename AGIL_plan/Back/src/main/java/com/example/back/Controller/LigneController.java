package com.example.back.controller;

import com.example.back.entity.Ligne_de_commande;
import com.example.back.service.LigneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lignes")
public class LigneController {

    @Autowired
    private LigneService ligneService;

    // Create a new Ligne_de_commande
    @PostMapping
    public ResponseEntity<Ligne_de_commande> createLigneDeCommande(@RequestBody Ligne_de_commande ligneDeCommande) {
        Ligne_de_commande createdLigne = ligneService.createLigneDeCommande(ligneDeCommande);
        if (createdLigne != null) {
            return new ResponseEntity<>(createdLigne, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Retrieve a Ligne_de_commande by ID
    @GetMapping("/{id}")
    public ResponseEntity<Ligne_de_commande> getLigneDeCommandeById(@PathVariable int id) {
        Ligne_de_commande ligneDeCommande = ligneService.getLigneDeCommandeById(id);
        if (ligneDeCommande != null) {
            return new ResponseEntity<>(ligneDeCommande, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update an existing Ligne_de_commande by ID
    @PutMapping("/{id}")
    public ResponseEntity<Ligne_de_commande> updateLigneDeCommande(@PathVariable int id, @RequestBody Ligne_de_commande updatedLigneDeCommande) {
        Ligne_de_commande updatedLigne = ligneService.updateLigneDeCommande(id, updatedLigneDeCommande);
        if (updatedLigne != null) {
            return new ResponseEntity<>(updatedLigne, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a Ligne_de_commande by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLigneDeCommande(@PathVariable int id) {
        ligneService.deleteLigneDeCommande(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Retrieve all Lignes_de_commande
    @GetMapping
    public ResponseEntity<List<Ligne_de_commande>> getAllLignesDeCommande() {
        List<Ligne_de_commande> lignesDeCommande = ligneService.getAllLignesDeCommande();
        return new ResponseEntity<>(lignesDeCommande, HttpStatus.OK);
    }

    // Retrieve Lignes_de_commande by Commande ID
    @GetMapping("/commande/{commandeId}")
    public ResponseEntity<List<Ligne_de_commande>> getLignesDeCommandeByCommandeId(@PathVariable int commandeId) {
        List<Ligne_de_commande> lignesDeCommande = ligneService.getLignesDeCommandeByCommandeId(commandeId);
        return new ResponseEntity<>(lignesDeCommande, HttpStatus.OK);
    }

    // Retrieve Lignes_de_commande by Produit ID
    @GetMapping("/produit/{produitId}")
    public ResponseEntity<List<Ligne_de_commande>> getLignesDeCommandeByProduitId(@PathVariable int produitId) {
        List<Ligne_de_commande> lignesDeCommande = ligneService.getLignesDeCommandeByProduitId(produitId);
        return new ResponseEntity<>(lignesDeCommande, HttpStatus.OK);
    }

    // Check stock availability for a specific produit and quantity
    @GetMapping("/produit/{produitId}/check-stock")
    public ResponseEntity<Boolean> checkStockAvailability(@PathVariable int produitId, @RequestParam int quantite) {
        boolean isAvailable = ligneService.checkStockAvailability(produitId, quantite);
        return new ResponseEntity<>(isAvailable, HttpStatus.OK);
    }
}
