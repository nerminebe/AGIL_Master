package org.example.mappers;

import lombok.RequiredArgsConstructor;
import org.example.daos.entities.User;
import org.example.dtos.views.UserDto;
import org.example.payload.requests.CreateUserRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper;

    public User toEntity(CreateUserRequest createUserRequest) {
        return modelMapper.map(createUserRequest, User.class);
    }

    public UserDto toUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

}
