package org.example.services;

import org.example.dtos.views.UserDto;
import org.example.enums.Role;
import org.example.payload.requests.CreateUserRequest;

import java.util.List;

public interface UserService {

    UserDto create(CreateUserRequest createUserRequest);

    UserDto findById(Long userId);

    UserDto findByUsername(String username);

    List<UserDto> findAll();

    void grantRoleToUser(Long userId, Role role);

    void revokeRoleFromUser(Long userId, Role role);

}
