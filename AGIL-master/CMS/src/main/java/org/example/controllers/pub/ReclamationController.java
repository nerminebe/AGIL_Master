package org.example.controllers.pub;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.example.dtos.views.ReclamationDTO;
import org.example.services.ReclamationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reclamations")
@RequiredArgsConstructor
public class ReclamationController {

    private final ReclamationService reclamationService;

    @GetMapping
    public ResponseEntity<List<ReclamationDTO>> getAllReclamations() {
        List<ReclamationDTO> reclamations = reclamationService.getAllReclamations();
        return ResponseEntity.ok(reclamations);
    }

    @GetMapping("/{reclamationId}")
    public ResponseEntity<ReclamationDTO> getReclamationById(@Parameter(name = "reclamationId") @PathVariable("reclamationId") Long reclamationId) {
        ReclamationDTO reclamation = reclamationService.getReclamationById(reclamationId);
        return ResponseEntity.ok(reclamation);
    }

}
