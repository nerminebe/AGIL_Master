package org.example.controllers.console;


import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.example.dtos.views.BonTravailDTO;
import org.example.requests.CreateBTRequest;
import org.example.services.BonTravailService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("console/api/v1/bontravails")
@RequiredArgsConstructor
public class ConsoleBonTravailController {

    private final BonTravailService bonTravailService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    public ResponseEntity<BonTravailDTO> createBonTravail(@RequestBody CreateBTRequest createBTRequest) {
        BonTravailDTO createdBonTravail = bonTravailService.createBonTravail(createBTRequest);
        return ResponseEntity.ok(createdBonTravail);
    }

    @PutMapping("/{btId}/encours")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    public ResponseEntity<BonTravailDTO> transitionToEnCours(@Parameter(name = "btId") @PathVariable("btId") Long btId) {
        BonTravailDTO updatedBonTravail = bonTravailService.transitionToEnCours(btId);
        return ResponseEntity.ok(updatedBonTravail);
    }

    @PutMapping("/{btId}/termine")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    public ResponseEntity<BonTravailDTO> transitionToTermine(@Parameter(name = "btId") @PathVariable("btId") Long btId, @Parameter(name = "detailsIntervention") @RequestParam(name = "detailsIntervention") String detailsIntervention) {
        BonTravailDTO updatedBonTravail = bonTravailService.transitionToTermine(btId, detailsIntervention);
        return ResponseEntity.ok(updatedBonTravail);
    }

    @PutMapping("/{btId}/cloture")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    public ResponseEntity<BonTravailDTO> transitionToCloture(@Parameter(name = "btId") @PathVariable("btId") Long btId) {
        BonTravailDTO updatedBonTravail = bonTravailService.transitionToCloture(btId);
        return ResponseEntity.ok(updatedBonTravail);
    }
}
