package org.example.controllers.console;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.example.dtos.views.ReclamationDTO;
import org.example.enums.StatusReclamation;
import org.example.requests.CreateReclamationRequest;
import org.example.requests.UpdateReclamationRequest;
import org.example.services.ReclamationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("console/api/v1/reclamations")
@RequiredArgsConstructor
public class ConsoleReclamationController {

    private final ReclamationService reclamationService;


    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    public ResponseEntity<ReclamationDTO> createReclamation(@RequestBody CreateReclamationRequest request) {
        ReclamationDTO reclamationDTO = reclamationService.createReclamation(request);
        return new ResponseEntity<>(reclamationDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{reclamationId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    public ResponseEntity<ReclamationDTO> updateReclamation(@Parameter(name = "reclamationId") @PathVariable("reclamationId") Long reclamationId, @RequestBody UpdateReclamationRequest updateReclamationRequest) {
        ReclamationDTO reclamationDTO = reclamationService.updateReclamation(reclamationId, updateReclamationRequest);
        return ResponseEntity.ok(reclamationDTO);
    }

    @GetMapping("/{reclamationId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    public ResponseEntity<ReclamationDTO> getReclamationById(@Parameter(name = "reclamationId") @PathVariable("reclamationId") Long reclamationId) {
        ReclamationDTO reclamationDTO = reclamationService.getReclamationById(reclamationId);
        return ResponseEntity.ok(reclamationDTO);
    }

    @PutMapping("/{reclamationId}/status")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    public ResponseEntity<ReclamationDTO> updateStatus(@Parameter(name = "reclamationId") @PathVariable Long reclamationId, @RequestParam StatusReclamation status) {
        return ResponseEntity.ok(reclamationService.updateStatus(reclamationId, status));

    }

}
