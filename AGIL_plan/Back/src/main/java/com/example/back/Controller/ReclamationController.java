package com.example.back.controller;

import com.example.back.entity.Reclamation;
import com.example.back.service.ReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reclamations")
public class ReclamationController {

    @Autowired
    private ReclamationService reclamationService;

    // Create a new Reclamation
    @PostMapping
    public ResponseEntity<Reclamation> createReclamation(@RequestBody Reclamation reclamation) {
        Reclamation createdReclamation = reclamationService.createReclamation(reclamation);
        return new ResponseEntity<>(createdReclamation, HttpStatus.CREATED);
    }

    // Retrieve a Reclamation by ID
    @GetMapping("/{id}")
    public ResponseEntity<Reclamation> getReclamationById(@PathVariable int id) {
        Reclamation reclamation = reclamationService.getReclamationById(id);
        if (reclamation != null) {
            return new ResponseEntity<>(reclamation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update an existing Reclamation by ID
    @PutMapping("/{id}")
    public ResponseEntity<Reclamation> updateReclamation(@PathVariable int id, @RequestBody Reclamation updatedReclamation) {
        Reclamation updatedReclamationEntity = reclamationService.updateReclamation(id, updatedReclamation);
        if (updatedReclamationEntity != null) {
            return new ResponseEntity<>(updatedReclamationEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a Reclamation by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReclamation(@PathVariable int id) {
        reclamationService.deleteReclamation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Retrieve all Reclamations by Gerant ID
    @GetMapping("/gerant/{gerantId}")
    public ResponseEntity<List<Reclamation>> getReclamationsByGerantId(@PathVariable int gerantId) {
        List<Reclamation> reclamations = reclamationService.getReclamationsByGerantId(gerantId);
        return new ResponseEntity<>(reclamations, HttpStatus.OK);
    }
}
