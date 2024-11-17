package com.example.back.service;

import com.example.back.entity.Reclamation;
import com.example.back.repositories.ReclamationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReclamationService {

    @Autowired
    private ReclamationRepo reclamationRepository;

    public Reclamation createReclamation(Reclamation reclamation) {
        return reclamationRepository.save(reclamation);
    }

    public Reclamation getReclamationById(int id) {
        return reclamationRepository.findById(id).orElse(null);
    }

    public Reclamation updateReclamation(int id, Reclamation updatedReclamation) {
        Optional<Reclamation> optionalReclamation = reclamationRepository.findById(id);
        if (optionalReclamation.isPresent()) {
            Reclamation existingReclamation = optionalReclamation.get();
            existingReclamation.setType(updatedReclamation.getType());
            existingReclamation.setDescription(updatedReclamation.getDescription());
            existingReclamation.setDate(updatedReclamation.getDate());
            existingReclamation.setGerant(updatedReclamation.getGerant());
            return reclamationRepository.save(existingReclamation);
        } else {
            return null;
        }
    }

    public void deleteReclamation(int id) {
        reclamationRepository.deleteById(id);
    }

    public List<Reclamation> getReclamationsByGerantId(int gerantId) {
        return reclamationRepository.findByGerantId(gerantId);
    }
}