package com.example.back.controller;

import com.example.back.entity.Gerant;
import com.example.back.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/station")
public class StationController {

    @Autowired
    private StationService stationService;

    @PostMapping("/{stationServiceId}/assign-gerant/{gerantId}")
    public ResponseEntity<Gerant> assignGerantToStationService(
            @PathVariable Long stationServiceId,
            @PathVariable Long gerantId) {
        Gerant gerant = stationService.assignGerantToStationService(stationServiceId, gerantId);
        if (gerant != null) {
            return ResponseEntity.ok(gerant);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{stationServiceId}/gerants")
    public ResponseEntity<List<Gerant>> findGerantsByStationService(
            @PathVariable Long stationServiceId) {
        List<Gerant> gerants = stationService.findGerantsByStationService(stationServiceId);
        if (!gerants.isEmpty()) {
            return ResponseEntity.ok(gerants);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{stationServiceId}/gerants/count")
    public ResponseEntity<Integer> countGerantsForStationService(
            @PathVariable Long stationServiceId) {
        int count = stationService.countGerantsForStationService(stationServiceId);
        return ResponseEntity.ok(count);
    }

    @PutMapping("/transfer-gerant/{gerantId}/to/{newStationServiceId}")
    public ResponseEntity<Gerant> transferGerantToAnotherStationService(
            @PathVariable Long gerantId,
            @PathVariable Long newStationServiceId) {
        Gerant gerant = stationService.transferGerantToAnotherStationService(gerantId, newStationServiceId);
        if (gerant != null) {
            return ResponseEntity.ok(gerant);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
