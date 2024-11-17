package com.example.back.controller;

import com.example.back.entity.Commande;
import com.example.back.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    @PostMapping
    public ResponseEntity<Commande> createCommande(@RequestBody Commande commande) {
        Commande createdCommande = commandeService.createCommande(commande);
        return new ResponseEntity<>(createdCommande, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commande> getCommandeById(@PathVariable int id) {
        Commande commande = commandeService.getCommandeById(id);
        if (commande != null) {
            return new ResponseEntity<>(commande, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Commande> updateCommande(
            @PathVariable int id,
            @RequestBody Commande updatedCommande
    ) {
        Commande commande = commandeService.updateCommande(id, updatedCommande);
        if (commande != null) {
            return new ResponseEntity<>(commande, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommande(@PathVariable int id) {
        if (commandeService.getCommandeById(id) != null) {
            commandeService.deleteCommande(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/gerant/{gerantId}")
    public ResponseEntity<List<Commande>> getCommandesByGerantId(@PathVariable int gerantId) {
        List<Commande> commandes = commandeService.getCommandesByGerantId(gerantId);
        return new ResponseEntity<>(commandes, HttpStatus.OK);
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<Boolean> checkCommandeStatus(@PathVariable int id) {
        boolean status = commandeService.checkCommandeStatus(id);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
