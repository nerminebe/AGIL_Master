package org.example.mappers;

import lombok.RequiredArgsConstructor;
import org.example.daos.entities.Depot;
import org.example.dtos.views.DepotDto;
import org.example.dtos.views.UserDto;
import org.example.payload.requests.CreateDepotRequest;
import org.example.payload.requests.UpdateDepotRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DepotMapper {

    private final ModelMapper modelMapper;

    public Depot toEntity(CreateDepotRequest createDepotRequest) {
        return modelMapper.map(createDepotRequest, Depot.class);
    }

    public Depot toEntity(UpdateDepotRequest updateDepotRequest) {
        return modelMapper.map(updateDepotRequest, Depot.class);
    }

    public DepotDto toDepotDto(Depot depot) {
        DepotDto depotDto = modelMapper.map(depot, DepotDto.class);
        if (depot.getResponsable() != null)
            depotDto.setResponsable(modelMapper.map(depot.getResponsable(), UserDto.class));
        return depotDto;
    }


}
