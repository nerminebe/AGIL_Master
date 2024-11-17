package org.example.services;

import org.example.dtos.views.LigneCommandeDTO;
import org.example.payload.requests.UpdateLigneCommandeRequest;

import java.util.List;

public interface LigneCommandeService {

    List<LigneCommandeDTO> findAllByCommandeId(Long commandeId);

    LigneCommandeDTO updateLigneCommande(Long ligneCommandeId, UpdateLigneCommandeRequest request);

    void deleteLigneCommande(Long ligneCommandeId);
}
