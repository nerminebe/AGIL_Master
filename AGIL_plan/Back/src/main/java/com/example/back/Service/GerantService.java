package com.example.back.service;


import com.example.back.entity.Commande;
import com.example.back.entity.Gerant;
import com.example.back.entity.Reclamation;
import com.example.back.repositories.CommandeRepo;
import com.example.back.repositories.GerantRepo;
import com.example.back.repositories.ProduitRepo;
import com.example.back.repositories.ReclamationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GerantService {

    @Autowired
    private GerantRepo gerantRepository;

    @Autowired
    private CommandeRepo commandeRepository;

    @Autowired
    private ReclamationRepo reclamationRepository;

    @Autowired
    private ProduitRepo produitRepository;

    public Gerant createGerant(Gerant gerant) {
        return gerantRepository.save(gerant);
    }

    public Gerant getGerantById(int id) {
        return gerantRepository.findById(id).orElse(null);
    }

    public Gerant updateGerant(int id, Gerant updatedGerant) {
        Optional<Gerant> optionalGerant = gerantRepository.findById(id);
        if (optionalGerant.isPresent()) {
            Gerant existingGerant = optionalGerant.get();
            existingGerant.setNom(updatedGerant.getNom());
            existingGerant.setPrenom(updatedGerant.getPrenom());
            existingGerant.setTelephone(updatedGerant.getTelephone());
            existingGerant.setDepotLivreur(updatedGerant.getDepotLivreur());
            existingGerant.setCommercial(updatedGerant.getCommercial());

            // Ensure this is commented out or removed if not present in your entity
            // existingGerant.setStationService(updatedGerant.getStationService());

            return gerantRepository.save(existingGerant);
        } else {
            return null;
        }
    }

    public void deleteGerant(int id) {
        gerantRepository.deleteById(id);
    }

    public List<Gerant> getAllGerants() {
        return gerantRepository.findAll();
    }

    public Commande passerCommande(int gerantId, Commande commande) {
        Optional<Gerant> gerant = gerantRepository.findById(gerantId);
        if (gerant.isPresent()) {
            // Vérifier si le stock est bas avant de passer commande
            boolean stockDepleted = commande.getLignesDeCommande().stream()
                    .anyMatch(ligne -> produitRepository.findById(ligne.getProduit().getId())
                            .map(produit -> produit.getQuantite() < ligne.getQuantiteCommandee())
                            .orElse(false));

            if (stockDepleted) {
                // Logique pour gérer les cas où le stock est insuffisant
                // Par exemple : notifier le gérant ou passer une commande d'urgence
            }

            commande.setGerant(gerant.get());
            return commandeRepository.save(commande);
        } else {
            return null;
        }
    }

    public Reclamation enregistrerReclamation(int gerantId, Reclamation reclamation) {
        Optional<Gerant> gerant = gerantRepository.findById(gerantId);
        if (gerant.isPresent()) {
            reclamation.setGerant(gerant.get());
            return reclamationRepository.save(reclamation);
        } else {
            return null;
        }
    }

    public List<Commande> suivreCommande(int gerantId) {
        return commandeRepository.findByGerantId(gerantId);
    }
}