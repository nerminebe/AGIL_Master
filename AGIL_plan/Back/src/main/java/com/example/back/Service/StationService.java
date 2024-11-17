package com.example.back.service;

import com.example.back.entity.Gerant;
import com.example.back.entity.Station_service;
import com.example.back.repositories.GerantRepo;
import com.example.back.repositories.StationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StationService {

    @Autowired
    private StationRepo stationServiceRepository;

    @Autowired
    private GerantRepo gerantRepository;

    public Gerant assignGerantToStationService(Long stationServiceId, Long gerantId) {
        Optional<Station_service> optionalStationService = stationServiceRepository.findById(stationServiceId);
        Optional<Gerant> optionalGerant = gerantRepository.findById(gerantId);

        if (optionalStationService.isPresent() && optionalGerant.isPresent()) {
            Gerant gerant = optionalGerant.get();
            gerant.setStation_service(optionalStationService.get());
            return gerantRepository.save(gerant);
        } else {
            return null;
        }
    }

    // Advanced method: Find all Gerants associated with a particular Station Service
    public List<Gerant> findGerantsByStationService(Long stationServiceId) {
        return gerantRepository.findByStation_serviceId(stationServiceId);
    }

    // Advanced method: Calculate the total number of Gerants for a Station Service
    public int countGerantsForStationService(Long stationServiceId) {
        return gerantRepository.countByStation_serviceId(stationServiceId);
    }

    // Advanced method: Transfer a Gerant from one Station Service to another
    public Gerant transferGerantToAnotherStationService(Long gerantId, Long newStationServiceId) {
        Optional<Gerant> optionalGerant = gerantRepository.findById(gerantId);
        Optional<Station_service> optionalNewStationService = stationServiceRepository.findById(newStationServiceId);

        if (optionalGerant.isPresent() && optionalNewStationService.isPresent()) {
            Gerant gerant = optionalGerant.get();
            gerant.setStation_service(optionalNewStationService.get());
            return gerantRepository.save(gerant);
        } else {
            return null;
        }
    }
}
