package org.example.services;

import org.example.dtos.views.BonTravailDTO;
import org.example.requests.CreateBTRequest;

import java.util.List;

public interface BonTravailService {

    BonTravailDTO createBonTravail(CreateBTRequest createBTRequest);

    List<BonTravailDTO> getAll();

    BonTravailDTO getById(Long btId);

    List<BonTravailDTO> getAllByTechnicienId(Long techId);

    BonTravailDTO transitionToEnCours(Long btId);

    BonTravailDTO transitionToTermine(Long btId, String detailsIntervention);

    BonTravailDTO transitionToCloture(Long btId);

}
