package com.example.back.service;

import com.example.back.entity.Commande;
import com.example.back.entity.Intervenant;
import com.example.back.entity.Reclamation;
import com.example.back.repositories.CommandeRepo;
import com.example.back.repositories.IntervenantRepo;
import com.example.back.repositories.ReclamationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntervenantService {

    @Autowired
    private IntervenantRepo intervenantRepository;

    @Autowired
    private CommandeRepo commandeRepository;

    @Autowired
    private ReclamationRepo reclamationRepository;

    public void traiterCommande(int commandeId) {
        // Logic to process an order
        Commande commande = commandeRepository.findById(commandeId)
                .orElseThrow(() -> new RuntimeException("Commande not found"));
        // Implement order processing logic here
    }

    public List<Commande> visualiserCommandes(Intervenant intervenant) {
        // Logic to view orders associated with an intervenant
        return commandeRepository.findByIntervenant(intervenant);
    }

    public void affecterReclamation(int reclamationId, Intervenant intervenant) {
        // Logic to assign a complaint to an intervenant
        Reclamation reclamation = reclamationRepository.findById(reclamationId)
                .orElseThrow(() -> new RuntimeException("Reclamation not found"));
        reclamation.setIntervenant(intervenant);
        reclamationRepository.save(reclamation);
    }

    public void suivreReclamations(int reclamationId) {
        // Logic to track a complaint
        Reclamation reclamation = reclamationRepository.findById(reclamationId)
                .orElseThrow(() -> new RuntimeException("Reclamation not found"));
        // Implement tracking logic here
    }
}