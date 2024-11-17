package org.example.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.daos.entities.Station;
import org.example.daos.entities.User;
import org.example.daos.repositories.StationRepository;
import org.example.daos.repositories.UserRepository;
import org.example.dtos.views.StationDto;
import org.example.exceptions.DataNotFoundException;
import org.example.mappers.StationMapper;
import org.example.payload.requests.CreateStationRequest;
import org.example.payload.requests.UpdateStationRequest;
import org.example.services.StationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;
    private final StationMapper stationMapper;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<StationDto> getAll() {
        return stationRepository.findAll().stream().map(stationMapper::toStationDto).toList();
    }

    @Override
    public StationDto create(CreateStationRequest createStationRequest) {
        Station station = stationMapper.toEntity(createStationRequest);
        if (createStationRequest.getGerant_id() != null) {
            User user = userRepository.findById(createStationRequest.getGerant_id()).orElseThrow(() -> new DataNotFoundException("Gerant not found"));
            station.setGerant(user);
        }
        return stationMapper.toStationDto(stationRepository.save(station));
    }

    @Override
    public StationDto findById(Long stationId) {
        return stationMapper.toStationDto(stationRepository.findById(stationId).orElseThrow(() -> new DataNotFoundException("Station not found")));
    }

    @Override
    public StationDto update(Long stationId, UpdateStationRequest updateStationRequest) {
        Station station = stationRepository.findById(stationId).orElseThrow(() -> new DataNotFoundException("Station not found"));
        modelMapper.map(updateStationRequest, station);
        return stationMapper.toStationDto(stationRepository.save(station));
    }

    @Override
    public void affecterGerantStation(Long stationId, Long gerantId) {
        Station station = stationRepository.findById(stationId).orElseThrow(() -> new DataNotFoundException("Station not found"));
        User user = userRepository.findById(gerantId).orElseThrow(() -> new DataNotFoundException("User not found"));
        station.setGerant(user);
        stationRepository.save(station);
    }
}
