package com.example.back.service;

import com.example.back.entity.Commercial;
import com.example.back.repositories.CommercialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommercialService {

    @Autowired
    private CommercialRepo commercialRepository;

    public Commercial createCommercial(Commercial commercial) {
        return commercialRepository.save(commercial);
    }

    public Commercial getCommercialById(int id) {
        return commercialRepository.findById(id).orElse(null);
    }

    public Commercial updateCommercial(int id, Commercial updatedCommercial) {
        Optional<Commercial> optionalCommercial = commercialRepository.findById(id);
        if (optionalCommercial.isPresent()) {
            Commercial existingCommercial = optionalCommercial.get();
            existingCommercial.setNom(updatedCommercial.getNom());
            existingCommercial.setPrenom(updatedCommercial.getPrenom());
            existingCommercial.setType(updatedCommercial.getType());
            return commercialRepository.save(existingCommercial);
        } else {
            return null;
        }
    }

    public void deleteCommercial(int id) {
        commercialRepository.deleteById(id);
    }

    public List<Commercial> getAllCommercials() {
        return commercialRepository.findAll();
    }
}
