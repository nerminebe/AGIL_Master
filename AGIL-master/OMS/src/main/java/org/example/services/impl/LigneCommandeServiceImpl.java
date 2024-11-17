package org.example.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.daos.entities.LigneCommande;
import org.example.daos.repositories.LigneCommandeRepository;
import org.example.dtos.views.LigneCommandeDTO;
import org.example.exceptions.ResourceNotFoundException;
import org.example.mappers.LigneCommandeMapper;
import org.example.payload.requests.UpdateLigneCommandeRequest;
import org.example.services.CommandeService;
import org.example.services.LigneCommandeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LigneCommandeServiceImpl implements LigneCommandeService {

    private final LigneCommandeRepository ligneCommandeRepository;
    private final LigneCommandeMapper ligneCommandeMapper;
    private final CommandeService commandeService;

    @Override
    public List<LigneCommandeDTO> findAllByCommandeId(Long commandeId) {
        List<LigneCommande> ligneCommandes = ligneCommandeRepository.findAllByCommandeId(commandeId);
        return ligneCommandes.stream()
                .map(ligneCommandeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LigneCommandeDTO updateLigneCommande(Long ligneCommandeId, UpdateLigneCommandeRequest request) {
        LigneCommande ligneCommande = ligneCommandeRepository.findById(ligneCommandeId)
                .orElseThrow(() -> new ResourceNotFoundException("LigneCommande not found"));

        ligneCommande.setQuantite(request.getQuantite());
        ligneCommande.setPrixTotal(ligneCommande.getProduit().getPrixUnitaire() * request.getQuantite());
        LigneCommande updatedLigneCommande = ligneCommandeRepository.save(ligneCommande);
        commandeService.updateCommandeTotalPrice(updatedLigneCommande.getCommande().getId());
        return ligneCommandeMapper.toDTO(updatedLigneCommande);
    }

    @Override
    public void deleteLigneCommande(Long id) {
        LigneCommande ligneCommande = ligneCommandeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LigneCommande not found"));
        ligneCommandeRepository.delete(ligneCommande);
    }
}
