package org.example.mappers;

import lombok.RequiredArgsConstructor;
import org.example.daos.entities.Reclamation;
import org.example.dtos.views.ReclamationDTO;
import org.example.dtos.views.UserDto;
import org.example.requests.CreateReclamationRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReclamationMapper {

    private final ModelMapper modelMapper;

    public ReclamationDTO toDTO(Reclamation reclamation) {
        ReclamationDTO reclamationDTO = modelMapper.map(reclamation, ReclamationDTO.class);
        reclamationDTO.setGerant(modelMapper.map(reclamation.getGerant(), UserDto.class));
        return reclamationDTO;
    }

    public Reclamation toEntity(CreateReclamationRequest createReclamationRequest) {
        return modelMapper.map(createReclamationRequest, Reclamation.class);
    }


}
