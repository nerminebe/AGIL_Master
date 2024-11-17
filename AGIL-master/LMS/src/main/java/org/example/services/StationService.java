package org.example.services;

import org.example.dtos.views.StationDto;
import org.example.payload.requests.CreateStationRequest;
import org.example.payload.requests.UpdateStationRequest;

import java.util.List;

public interface StationService {

    List<StationDto> getAll();

    StationDto create(CreateStationRequest createStationRequest);

    StationDto findById(Long stationId);

    StationDto update(Long stationId, UpdateStationRequest updateStationRequest);

    void affecterGerantStation(Long stationId, Long gerantId);
}
