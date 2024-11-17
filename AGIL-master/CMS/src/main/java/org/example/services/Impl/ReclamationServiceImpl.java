package org.example.services.Impl;

import lombok.RequiredArgsConstructor;
import org.example.daos.entities.Reclamation;
import org.example.daos.entities.User;
import org.example.daos.repositories.ReclamationRepository;
import org.example.daos.repositories.UserRepository;
import org.example.dtos.views.ReclamationDTO;
import org.example.enums.StatusReclamation;
import org.example.exceptions.ResourceNotFoundException;
import org.example.mappers.ReclamationMapper;
import org.example.requests.CreateReclamationRequest;
import org.example.requests.UpdateReclamationRequest;
import org.example.services.ReclamationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReclamationServiceImpl implements ReclamationService {

    private final ReclamationRepository reclamationRepository;
    private final ReclamationMapper reclamationMapper;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Override
    public ReclamationDTO createReclamation(CreateReclamationRequest request) {
        User gerant = userRepository.findById(request.getGerantId())
                .orElseThrow(() -> new ResourceNotFoundException("Gerant not found with Id " + request.getGerantId()));
        Reclamation reclamation = reclamationMapper.toEntity(request);
        reclamation.setGerant(gerant);
        reclamation.setStatus(StatusReclamation.EN_COURS_DE_TRAITEMENT);
        reclamation.setDateReclamation(new Date());
        return reclamationMapper.toDTO(reclamationRepository.save(reclamation));
    }

    @Override
    public ReclamationDTO updateReclamation(Long reclamationId, UpdateReclamationRequest request) {
        Reclamation reclamation = reclamationRepository.findById(reclamationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reclamation not found with id " + reclamationId));
        modelMapper.map(request, reclamation);
        return reclamationMapper.toDTO(reclamationRepository.save(reclamation));
    }

    @Override
    public ReclamationDTO getReclamationById(Long id) {
        Reclamation reclamation = reclamationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reclamation not found with id " + id));
        return reclamationMapper.toDTO(reclamation);
    }

    @Override
    public List<ReclamationDTO> getAllReclamations() {
        List<Reclamation> reclamations = reclamationRepository.findAll();
        return reclamations.stream()
                .map(reclamationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReclamationDTO updateStatus(Long id, StatusReclamation newStatus) {
        Reclamation reclamation = reclamationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reclamation not found with id " + id));

        StatusReclamation currentStatus = reclamation.getStatus();

        // Validayion des transistions
        switch (newStatus) {
            case TRAITEE:
                if (currentStatus != StatusReclamation.EN_COURS_DE_TRAITEMENT) {
                    throw new IllegalArgumentException("La réclamation doit être en cours de traitement pour être traitée.");
                }
                reclamation.setTraiteeLe(new Date());
                break;
            case NON_VALIDEE:
                if (currentStatus != StatusReclamation.TRAITEE) {
                    throw new IllegalArgumentException("La réclamation doit être traitée pour être non validée.");
                }
                reclamation.setNonValideeLe(new Date());
                break;
            case CLOTUREE:
                if (currentStatus != StatusReclamation.TRAITEE && currentStatus != StatusReclamation.NON_VALIDEE) {
                    throw new IllegalArgumentException("La réclamation doit être traitée ou non validée pour être clôturée.");
                }
                reclamation.setClotureeLe(new Date());
                break;
            default:
                throw new IllegalArgumentException("Transition de statut non valide.");
        }

        reclamation.setStatus(newStatus);
        return reclamationMapper.toDTO(reclamationRepository.save(reclamation));
    }
}
