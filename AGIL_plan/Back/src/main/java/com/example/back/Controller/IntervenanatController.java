package com.example.back.controller;
import com.example.back.entity.Commande;
import com.example.back.entity.Intervenant;
import com.example.back.service.IntervenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/intervenants")
public class IntervenanatController {




        @Autowired
        private IntervenantService intervenantService;

        // Process an order by ID
        @PostMapping("/commandes/{commandeId}/traiter")
        public ResponseEntity<Void> traiterCommande(@PathVariable int commandeId) {
            try {
                intervenantService.traiterCommande(commandeId);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (RuntimeException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        // View orders associated with an Intervenant
        @GetMapping("/{intervenantId}/commandes")
        public ResponseEntity<List<Commande>> visualiserCommandes(@PathVariable int intervenantId) {
            Intervenant intervenant = new Intervenant();  // Assuming you have a way to retrieve or instantiate an Intervenant by ID
            intervenant.setId(intervenantId);             // Set the ID here

            List<Commande> commandes = intervenantService.visualiserCommandes(intervenant);
            return new ResponseEntity<>(commandes, HttpStatus.OK);
        }

        // Assign a complaint to an Intervenant
        @PostMapping("/reclamations/{reclamationId}/affecter/{intervenantId}")
        public ResponseEntity<Void> affecterReclamation(@PathVariable int reclamationId, @PathVariable int intervenantId) {
            try {
                Intervenant intervenant = new Intervenant();  // Assuming you have a way to retrieve or instantiate an Intervenant by ID
                intervenant.setId(intervenantId);             // Set the ID here

                intervenantService.affecterReclamation(reclamationId, intervenant);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (RuntimeException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        // Track a complaint by ID
        @GetMapping("/reclamations/{reclamationId}/suivre")
        public ResponseEntity<Void> suivreReclamations(@PathVariable int reclamationId) {
            try {
                intervenantService.suivreReclamations(reclamationId);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (RuntimeException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
}
