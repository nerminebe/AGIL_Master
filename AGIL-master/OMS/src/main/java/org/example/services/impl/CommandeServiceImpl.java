package org.example.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.daos.entities.Commande;
import org.example.daos.entities.LigneCommande;
import org.example.daos.entities.Produit;
import org.example.daos.entities.User;
import org.example.daos.repositories.CommandeRepository;
import org.example.daos.repositories.LigneCommandeRepository;
import org.example.daos.repositories.ProduitRepository;
import org.example.daos.repositories.UserRepository;
import org.example.dtos.views.CommandeDTO;
import org.example.enums.EtatCommande;
import org.example.exceptions.ResourceNotFoundException;
import org.example.mappers.CommandeMapper;
import org.example.payload.requests.CreateCommandeRequest;
import org.example.payload.requests.CreateLigneCommandeRequest;
import org.example.payload.requests.UpdateCommandeRequest;
import org.example.services.CommandeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommandeServiceImpl implements CommandeService {

    private final CommandeRepository commandeRepository;
    private final UserRepository userRepository;
    private final ProduitRepository produitRepository;
    private final LigneCommandeRepository ligneCommandeRepository;
    private final CommandeMapper commandeMapper;
    private final ModelMapper modelMapper;


    @Override
    public CommandeDTO createCommande(CreateCommandeRequest request) {
        User gerant = userRepository.findById(request.getGerantId())
                .orElseThrow(() -> new ResourceNotFoundException("Gerant not found"));

        Commande commande = new Commande();
        commande.setGerant(gerant);
        commande.setDateLivraison(request.getDateLivraison());

        double totalAmount = 0;
        for (CreateLigneCommandeRequest ligneCommandeRequest : request.getLigneCommandes()) {
            Produit produit = produitRepository.findById(ligneCommandeRequest.getProduitId())
                    .orElseThrow(() -> new ResourceNotFoundException("Produit not found"));
            LigneCommande ligneCommande = new LigneCommande();
            ligneCommande.setCommande(commande);
            ligneCommande.setProduit(produit);
            ligneCommande.setQuantite(ligneCommandeRequest.getQuantite());
            ligneCommande.setPrixTotal(produit.getPrixUnitaire() * ligneCommandeRequest.getQuantite());
            totalAmount += ligneCommande.getPrixTotal();
            ligneCommandeRepository.save(ligneCommande);
        }

        commande.setMontantTotal(totalAmount);
        commande.setStatus(EtatCommande.EN_COURS_DE_PROGRAMMATION);

        Commande savedCommande = commandeRepository.save(commande);

        return commandeMapper.toDTO(savedCommande);
    }

    @Override
    public CommandeDTO getCommandeById(Long id) {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commande not found"));
        return commandeMapper.toDTO(commande);
    }

    @Override
    public List<CommandeDTO> getAllCommandes() {
        List<Commande> commandes = commandeRepository.findAll();
        return commandes.stream().map(commandeMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public CommandeDTO updateCommande(Long id, UpdateCommandeRequest updateCommandeRequest) {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commande not found"));
        modelMapper.map(updateCommandeRequest, commande);
        return commandeMapper.toDTO(commandeRepository.save(commande));
    }

    @Override
    public void deleteCommande(Long id) {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commande not found"));
        commandeRepository.delete(commande);
    }

    @Override
    public void updateCommandeTotalPrice(Long commandeId) {
        Commande commande = commandeRepository.findById(commandeId)
                .orElseThrow(() -> new ResourceNotFoundException("Commande not found"));
        List<LigneCommande> ligneCommandes = ligneCommandeRepository.findAllByCommandeId(commande.getId());
        double totalPrix = ligneCommandes.stream()
                .mapToDouble(LigneCommande::getPrixTotal)
                .sum();
        commande.setMontantTotal(totalPrix);
        commandeRepository.save(commande);
    }

}
