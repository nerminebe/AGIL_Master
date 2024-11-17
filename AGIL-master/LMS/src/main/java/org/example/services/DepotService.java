package org.example.services;

import org.example.dtos.views.DepotDto;
import org.example.dtos.views.StationDto;
import org.example.payload.requests.CreateDepotRequest;
import org.example.payload.requests.UpdateDepotRequest;

import java.util.List;

public interface DepotService {

    List<DepotDto> getAll();

    DepotDto create(CreateDepotRequest createDepotRequest);

    DepotDto getbyId(Long depotId);

    DepotDto update(Long depotId, UpdateDepotRequest updateDepotRequest);

    void affecterResponsableDepot(Long depotId, Long responsableId);

}
