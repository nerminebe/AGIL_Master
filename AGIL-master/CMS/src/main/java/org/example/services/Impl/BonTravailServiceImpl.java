package org.example.services.Impl;

import lombok.RequiredArgsConstructor;
import org.example.daos.entities.BonTravail;
import org.example.daos.entities.Reclamation;
import org.example.daos.entities.User;
import org.example.daos.repositories.BonTravailRepository;
import org.example.daos.repositories.ReclamationRepository;
import org.example.daos.repositories.UserRepository;
import org.example.dtos.views.BonTravailDTO;
import org.example.enums.StatusBonTravail;
import org.example.exceptions.ResourceNotFoundException;
import org.example.mappers.BonTravailMapper;
import org.example.requests.CreateBTRequest;
import org.example.services.BonTravailService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BonTravailServiceImpl implements BonTravailService {

    private final BonTravailRepository bonTravailRepository;
    private final ReclamationRepository reclamationRepository;
    private final UserRepository userRepository;
    private final BonTravailMapper bonTravailMapper;

    @Override
    public BonTravailDTO createBonTravail(CreateBTRequest createBTRequest) {
        User technicien = userRepository.findById(createBTRequest.getTechnicienId())
                .orElseThrow(() -> new ResourceNotFoundException("Gerant not found with Id " + createBTRequest.getTechnicienId()));
        Reclamation reclamation = reclamationRepository.findById(createBTRequest.getReclamationId())
                .orElseThrow(() -> new ResourceNotFoundException("Reclamation not found with id " + createBTRequest.getReclamationId()));

        BonTravail bonTravail = new BonTravail();
        bonTravail.setTechnicien(technicien);
        bonTravail.setReclamation(reclamation);
        return bonTravailMapper.toDto(bonTravailRepository.save(bonTravail));
    }

    @Override
    public List<BonTravailDTO> getAll() {
        return bonTravailRepository.findAll().stream().map(bonTravailMapper::toDto).toList();
    }

    @Override
    public BonTravailDTO getById(Long btId) {
        return bonTravailMapper.toDto(bonTravailRepository.findById(btId)
                .orElseThrow(() -> new ResourceNotFoundException("BT not found with Id " + btId)));
    }

    @Override
    public List<BonTravailDTO> getAllByTechnicienId(Long techId) {
        return bonTravailRepository.findByTechnicien_Id(techId).stream().map(bonTravailMapper::toDto).toList();
    }

    @Override
    public BonTravailDTO transitionToEnCours(Long btId) {
        BonTravail bonTravail = bonTravailRepository.findById(btId)
                .orElseThrow(() -> new RuntimeException("BonTravail not found"));
        bonTravail.setStatus(StatusBonTravail.EN_COURS);
        BonTravail updatedBonTravail = bonTravailRepository.save(bonTravail);
        return bonTravailMapper.toDto(updatedBonTravail);
    }

    @Override
    public BonTravailDTO transitionToTermine(Long btId, String detailsIntervention) {
        BonTravail bonTravail = bonTravailRepository.findById(btId)
                .orElseThrow(() -> new RuntimeException("BonTravail not found"));
        bonTravail.setStatus(StatusBonTravail.TERMINE);
        bonTravail.setDetailsIntervention(detailsIntervention);
        bonTravail.setTermineLe(new Date());
        BonTravail updatedBonTravail = bonTravailRepository.save(bonTravail);
        return bonTravailMapper.toDto(updatedBonTravail);
    }

    @Override
    public BonTravailDTO transitionToCloture(Long btId) {
        BonTravail bonTravail = bonTravailRepository.findById(btId)
                .orElseThrow(() -> new RuntimeException("BonTravail not found"));
        bonTravail.setStatus(StatusBonTravail.CLOTURE);
        bonTravail.setClotureLe(new Date());
        BonTravail updatedBonTravail = bonTravailRepository.save(bonTravail);
        return bonTravailMapper.toDto(updatedBonTravail);
    }
}
