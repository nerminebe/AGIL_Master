package org.example.mappers;

import lombok.RequiredArgsConstructor;
import org.example.daos.entities.BonTravail;
import org.example.dtos.views.BonTravailDTO;
import org.example.dtos.views.ReclamationDTO;
import org.example.dtos.views.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BonTravailMapper {

    private final ModelMapper modelMapper;

    public BonTravailDTO toDto(BonTravail bonTravail) {
        BonTravailDTO bonTravailDTO = modelMapper.map(bonTravail, BonTravailDTO.class);
        bonTravailDTO.setTechnicien(modelMapper.map(bonTravail.getTechnicien(), UserDto.class));
        bonTravailDTO.setReclamation(modelMapper.map(bonTravail.getReclamation(), ReclamationDTO.class));
        return bonTravailDTO;
    }


}
