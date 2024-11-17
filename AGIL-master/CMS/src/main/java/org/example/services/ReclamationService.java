package org.example.services;

import org.example.daos.entities.Reclamation;
import org.example.dtos.views.ReclamationDTO;
import org.example.enums.StatusReclamation;
import org.example.requests.CreateReclamationRequest;
import org.example.requests.UpdateReclamationRequest;

import java.util.List;

public interface ReclamationService {

    ReclamationDTO createReclamation(CreateReclamationRequest request);

    ReclamationDTO updateReclamation(Long reclamationId, UpdateReclamationRequest request);

    ReclamationDTO getReclamationById(Long id);

    List<ReclamationDTO> getAllReclamations();

    ReclamationDTO updateStatus(Long id, StatusReclamation newStatus);

}
