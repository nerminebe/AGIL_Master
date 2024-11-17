package com.example.back.service;

import com.example.back.entity.Commande;
import com.example.back.repositories.CommandeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeService {

    @Autowired
    private CommandeRepo commandeRepository;

    public Commande createCommande(Commande commande) {
        // Save the new Commande entity
        return commandeRepository.save(commande);
    }

    public Commande getCommandeById(int id) {
        return commandeRepository.findById(id).orElse(null);
    }

    public Commande updateCommande(int id, Commande updatedCommande) {
        Optional<Commande> optionalCommande = commandeRepository.findById(id);
        if (optionalCommande.isPresent()) {
            Commande existingCommande = optionalCommande.get();
            // Update only the fields that are not null in the updatedCommande
            if (updatedCommande.getDate() != null) {
                existingCommande.setDate(updatedCommande.getDate());
            }
            if (updatedCommande.getEtat() != null) {
                existingCommande.setEtat(updatedCommande.getEtat());
            }
            if (updatedCommande.getMontant() != 0) {
                existingCommande.setMontant(updatedCommande.getMontant());
            }
            if (updatedCommande.getNomDuChauffeur() != null) {
                existingCommande.setNomDuChauffeur(updatedCommande.getNomDuChauffeur());
            }
            if (updatedCommande.getTel() != null) {
                existingCommande.setTel(updatedCommande.getTel());
            }
            if (updatedCommande.getReferenceCommande() != null) {
                existingCommande.setReferenceCommande(updatedCommande.getReferenceCommande());
            }
            if (updatedCommande.getGerant() != null) {
                existingCommande.setGerant(updatedCommande.getGerant());
            }
            if (updatedCommande.getLignesDeCommande() != null) {
                existingCommande.setLignesDeCommande(updatedCommande.getLignesDeCommande());
            }
            return commandeRepository.save(existingCommande);
        } else {
            return null;
        }
    }

    public void deleteCommande(int id) {
        if (commandeRepository.existsById(id)) {
            commandeRepository.deleteById(id);
        }
    }

    public List<Commande> getCommandesByGerantId(int gerantId) {
        // Implement the query in CommandeRepo if not already done
        return commandeRepository.findByGerantId(gerantId);
    }

    public boolean checkCommandeStatus(int commandeId) {
        Optional<Commande> commande = commandeRepository.findById(commandeId);
        return commande.isPresent() && "EN_COURS".equals(commande.get().getEtat().name());
    }
}
