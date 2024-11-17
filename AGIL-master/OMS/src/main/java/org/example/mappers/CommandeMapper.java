package org.example.mappers;

import lombok.RequiredArgsConstructor;
import org.example.daos.entities.Commande;
import org.example.dtos.views.CommandeDTO;
import org.example.dtos.views.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandeMapper {

    private final ModelMapper modelMapper;

    public CommandeDTO toDTO(Commande commande) {
        CommandeDTO commandeDTO = modelMapper.map(commande, CommandeDTO.class);
        commandeDTO.setGerant(modelMapper.map(commande.getGerant(), UserDto.class));
        return commandeDTO;
    }

}
