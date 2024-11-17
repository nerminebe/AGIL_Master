package org.example.controllers.console;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.example.dtos.views.StationDto;
import org.example.payload.requests.CreateStationRequest;
import org.example.payload.requests.UpdateStationRequest;
import org.example.services.StationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("console/api/v1/stations")
@RequiredArgsConstructor
public class ConsoleStationController {

    private final StationService stationService;

    @GetMapping
    public ResponseEntity<List<StationDto>> getAllStations() {
        List<StationDto> stations = stationService.getAll();
        return ResponseEntity.ok(stations);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    public ResponseEntity<StationDto> createStation(@RequestBody CreateStationRequest createStationRequest) {
        StationDto stationDto = stationService.create(createStationRequest);
        return new ResponseEntity<>(stationDto, HttpStatus.CREATED);
    }

    @GetMapping("/{stationId}")
    public ResponseEntity<StationDto> getStationById(@Parameter(name = "stationId") @PathVariable("stationId") Long stationId) {
        StationDto stationDto = stationService.findById(stationId);
        return ResponseEntity.ok(stationDto);
    }

    @PutMapping("/{stationId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    public ResponseEntity<StationDto> updateStation(@Parameter(name = "stationId") @PathVariable("stationId") Long stationId, @RequestBody UpdateStationRequest updateStationRequest) {
        StationDto updatedStation = stationService.update(stationId, updateStationRequest);
        return ResponseEntity.ok(updatedStation);
    }

    @PutMapping("/{stationId}/gerant/{gerantId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    public ResponseEntity<Void> assignGerant(@Parameter(name = "stationId") @PathVariable("stationId") Long stationId, @Parameter(name = "gerantId") @PathVariable("gerantId") Long gerantId) {
        stationService.affecterGerantStation(stationId, gerantId);
        return ResponseEntity.noContent().build();
    }

}
