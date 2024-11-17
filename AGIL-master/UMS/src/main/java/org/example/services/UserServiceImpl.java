package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.RabbitMq.RabbitMQProducer;
import org.example.daos.entities.User;
import org.example.daos.repositories.UserRepository;
import org.example.dtos.views.UserDto;
import org.example.enums.Role;
import org.example.exceptions.DataNotFoundException;
import org.example.exceptions.UnprocessableException;
import org.example.mappers.UserMapper;
import org.example.payload.requests.CreateUserRequest;
import org.example.utils.PasswordGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RabbitMQProducer rabbitMQProducer;


    @Override
    public UserDto create(CreateUserRequest createUserRequest) {
        if (userRepository.existsByUsername(createUserRequest.getUsername()))
            throw new UnprocessableException("username existant !");

        if (userRepository.existsByEmail(createUserRequest.getEmail()))
            throw new UnprocessableException("username existant !");

        User user = userMapper.toEntity(createUserRequest);

        user.setRoles(Set.of());
        String randompassword = PasswordGenerator.generateSecureRandomPassword();
        user.setPassword(passwordEncoder.encode(randompassword));

        user = userRepository.save(user);

        UserDto userDto = userMapper.toUserDto(user);

        if (createUserRequest.isNotifyCred())
            rabbitMQProducer.sendCredentialsMail(user.getEmail(), user.getUsername(), randompassword);
        return userDto;
    }

    @Override
    public UserDto findById(Long userId) {
        return userMapper.toUserDto(userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("user not found !")));
    }

    @Override
    public UserDto findByUsername(String username) {
        return userMapper.toUserDto(userRepository.findUserByUsername(username).orElseThrow(() -> new DataNotFoundException("user not found !")));
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(userMapper::toUserDto).toList();
    }

    @Override
    public void grantRoleToUser(Long userId, Role role) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        if (!user.getRoles().contains(role)) {
            user.getRoles().add(role);
            userRepository.save(user);
        }
    }

    @Override
    public void revokeRoleFromUser(Long userId, Role role) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        if (user.getRoles().contains(role)) {
            user.getRoles().remove(role);
            userRepository.save(user);
        }
    }

}
