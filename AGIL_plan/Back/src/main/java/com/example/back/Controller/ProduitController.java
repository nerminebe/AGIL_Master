package com.example.back.controller;

import com.example.back.entity.Produit;
import com.example.back.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    // Create a new Produit
    @PostMapping
    public ResponseEntity<Produit> createProduit(@RequestBody Produit produit) {
        Produit createdProduit = produitService.createProduit(produit);
        return new ResponseEntity<>(createdProduit, HttpStatus.CREATED);
    }

    // Retrieve a Produit by ID
    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable int id) {
        Produit produit = produitService.getProduitById(id);
        if (produit != null) {
            return new ResponseEntity<>(produit, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update an existing Produit by ID
    @PutMapping("/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable int id, @RequestBody Produit updatedProduit) {
        Produit updatedProduitEntity = produitService.updateProduit(id, updatedProduit);
        if (updatedProduitEntity != null) {
            return new ResponseEntity<>(updatedProduitEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a Produit by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable int id) {
        produitService.deleteProduit(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Retrieve all Produits
    @GetMapping
    public ResponseEntity<List<Produit>> getAllProduits() {
        List<Produit> produits = produitService.getAllProduits();
        return new ResponseEntity<>(produits, HttpStatus.OK);
    }

    // Check stock availability for a specific produit and quantity
    @GetMapping("/{produitId}/check-stock")
    public ResponseEntity<Boolean> checkStockAvailability(@PathVariable int produitId, @RequestParam int quantite) {
        boolean isAvailable = produitService.checkStockAvailability(produitId, quantite);
        return new ResponseEntity<>(isAvailable, HttpStatus.OK);
    }
}
