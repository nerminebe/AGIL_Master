package com.example.back.controller;

import com.example.back.entity.Commande;
import com.example.back.entity.Gerant;
import com.example.back.entity.Reclamation;
import com.example.back.service.GerantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gerants")
public class GerantController {

    @Autowired
    private GerantService gerantService;

    // Create a new Gerant
    @PostMapping
    public ResponseEntity<Gerant> createGerant(@RequestBody Gerant gerant) {
        Gerant createdGerant = gerantService.createGerant(gerant);
        return new ResponseEntity<>(createdGerant, HttpStatus.CREATED);
    }

    // Get a Gerant by ID
    @GetMapping("/{id}")
    public ResponseEntity<Gerant> getGerantById(@PathVariable int id) {
        Gerant gerant = gerantService.getGerantById(id);
        if (gerant != null) {
            return new ResponseEntity<>(gerant, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update a Gerant
    @PutMapping("/{id}")
    public ResponseEntity<Gerant> updateGerant(@PathVariable int id, @RequestBody Gerant updatedGerant) {
        Gerant gerant = gerantService.updateGerant(id, updatedGerant);
        if (gerant != null) {
            return new ResponseEntity<>(gerant, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a Gerant
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGerant(@PathVariable int id) {
        gerantService.deleteGerant(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get all Gerants
    @GetMapping
    public ResponseEntity<List<Gerant>> getAllGerants() {
        List<Gerant> gerants = gerantService.getAllGerants();
        return new ResponseEntity<>(gerants, HttpStatus.OK);
    }

    // Place a new Commande by Gerant ID
    @PostMapping("/{gerantId}/commandes")
    public ResponseEntity<Commande> passerCommande(@PathVariable int gerantId, @RequestBody Commande commande) {
        Commande createdCommande = gerantService.passerCommande(gerantId, commande);
        if (createdCommande != null) {
            return new ResponseEntity<>(createdCommande, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Register a new Reclamation by Gerant ID
    @PostMapping("/{gerantId}/reclamations")
    public ResponseEntity<Reclamation> enregistrerReclamation(@PathVariable int gerantId, @RequestBody Reclamation reclamation) {
        Reclamation createdReclamation = gerantService.enregistrerReclamation(gerantId, reclamation);
        if (createdReclamation != null) {
            return new ResponseEntity<>(createdReclamation, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get all Commandes for a specific Gerant
    @GetMapping("/{gerantId}/commandes")
    public ResponseEntity<List<Commande>> suivreCommande(@PathVariable int gerantId) {
        List<Commande> commandes = gerantService.suivreCommande(gerantId);
        return new ResponseEntity<>(commandes, HttpStatus.OK);
    }
}
