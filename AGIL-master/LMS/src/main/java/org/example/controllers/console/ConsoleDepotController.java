package org.example.controllers.console;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.example.dtos.views.DepotDto;
import org.example.payload.requests.CreateDepotRequest;
import org.example.payload.requests.UpdateDepotRequest;
import org.example.services.DepotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("console/api/v1/depots")
@RequiredArgsConstructor
public class ConsoleDepotController {

    private final DepotService depotService;

    @GetMapping
    public ResponseEntity<List<DepotDto>> getAllDepots() {
        List<DepotDto> depots = depotService.getAll();
        return ResponseEntity.ok(depots);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    public ResponseEntity<DepotDto> createDepot(@RequestBody CreateDepotRequest createDepotRequest) {
        DepotDto depotDto = depotService.create(createDepotRequest);
        return new ResponseEntity<>(depotDto, HttpStatus.CREATED);
    }

    @GetMapping("/{depotId}")
    public ResponseEntity<DepotDto> getDepotById(@Parameter(name = "depotId") @PathVariable("depotId") Long depotId) {
        DepotDto depotDto = depotService.getbyId(depotId);
        return ResponseEntity.ok(depotDto);
    }

    @PutMapping("/{depotId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    public ResponseEntity<DepotDto> updateDepot(@Parameter(name = "depotId") @PathVariable("depotId") Long depotId, @RequestBody UpdateDepotRequest updateDepotRequest) {
        DepotDto updatedDepot = depotService.update(depotId, updateDepotRequest);
        return ResponseEntity.ok(updatedDepot);
    }

    @PutMapping("/{depotId}/responsable/{responsableId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    public ResponseEntity<Void> assignResponsable(@Parameter(name = "depotId") @PathVariable("depotId") Long depotId, @Parameter(name = "responsableId") @PathVariable("responsableId") Long responsableId) {
        depotService.affecterResponsableDepot(depotId, responsableId);
        return ResponseEntity.noContent().build();
    }
}
