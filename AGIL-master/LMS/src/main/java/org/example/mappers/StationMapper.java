package org.example.mappers;

import lombok.RequiredArgsConstructor;
import org.example.daos.entities.Station;
import org.example.dtos.views.StationDto;
import org.example.dtos.views.UserDto;
import org.example.payload.requests.CreateStationRequest;
import org.example.payload.requests.UpdateStationRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StationMapper {

    private final ModelMapper modelMapper;

    public Station toEntity(CreateStationRequest createStationRequest) {
        return modelMapper.map(createStationRequest, Station.class);
    }

    public Station toEntity(UpdateStationRequest updateStationRequest) {
        return modelMapper.map(updateStationRequest, Station.class);
    }

    public StationDto toStationDto(Station station) {
        StationDto stationDto = modelMapper.map(station, StationDto.class);
        if (station.getGerant() != null)
            stationDto.setGerant(modelMapper.map(station.getGerant(), UserDto.class));
        return stationDto;
    }

}
