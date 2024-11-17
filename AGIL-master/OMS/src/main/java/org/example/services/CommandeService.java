package org.example.services;

import org.example.dtos.views.CommandeDTO;
import org.example.payload.requests.CreateCommandeRequest;
import org.example.payload.requests.UpdateCommandeRequest;

import java.util.List;

public interface CommandeService {
    CommandeDTO createCommande(CreateCommandeRequest request);

    CommandeDTO getCommandeById(Long id);

    List<CommandeDTO> getAllCommandes();

    CommandeDTO updateCommande(Long id, UpdateCommandeRequest updateCommandeRequest);

    void deleteCommande(Long id);

    void updateCommandeTotalPrice(Long commandeId);
}
