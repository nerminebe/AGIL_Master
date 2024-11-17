package org.example.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.daos.entities.Depot;
import org.example.daos.entities.User;
import org.example.daos.repositories.DepotRepository;
import org.example.daos.repositories.UserRepository;
import org.example.dtos.views.DepotDto;
import org.example.exceptions.DataNotFoundException;
import org.example.mappers.DepotMapper;
import org.example.payload.requests.CreateDepotRequest;
import org.example.payload.requests.UpdateDepotRequest;
import org.example.services.DepotService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepotServiceImpl implements DepotService {

    private final DepotRepository depotRepository;
    private final DepotMapper depotMapper;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<DepotDto> getAll() {
        return depotRepository.findAll().stream().map(depotMapper::toDepotDto).toList();
    }

    @Override
    public DepotDto create(CreateDepotRequest createDepotRequest) {
        Depot depot = depotMapper.toEntity(createDepotRequest);
        if (createDepotRequest.getResponsable_id() != null) {
            User user = userRepository.findById(createDepotRequest.getResponsable_id()).orElseThrow(() -> new DataNotFoundException("Responsable not found"));
            depot.setResponsable(user);
        }
        return depotMapper.toDepotDto(depotRepository.save(depot));
    }

    @Override
    public DepotDto getbyId(Long depotId) {
        return depotMapper.toDepotDto(depotRepository.findById(depotId).orElseThrow(() -> new DataNotFoundException("Depot not found")));
    }

    @Override
    public DepotDto update(Long depotId, UpdateDepotRequest updateDepotRequest) {
        Depot depot = depotRepository.findById(depotId).orElseThrow(() -> new DataNotFoundException("Depot not found"));
        modelMapper.map(updateDepotRequest, depot);
        return depotMapper.toDepotDto(depotRepository.save(depot));
    }

    @Override
    public void affecterResponsableDepot(Long depotId, Long responsableId) {
        Depot depot = depotRepository.findById(depotId).orElseThrow(() -> new DataNotFoundException("Depot not found"));
        User user = userRepository.findById(responsableId).orElseThrow(() -> new DataNotFoundException("User not found"));
        depot.setResponsable(user);
        depotRepository.save(depot);
    }
}
