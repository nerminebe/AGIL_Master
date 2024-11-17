package org.example.utils;

import lombok.RequiredArgsConstructor;
import org.example.daos.entities.User;
import org.example.daos.repositories.UserRepository;
import org.example.enums.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Component
@RequiredArgsConstructor
public class DataInitializer {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    void init() {

        createUserIfNotExists("nerminbenmohamed@example.com", "nerminbenmohamed", "nerminbenmohamed", "Super", "Admin", Set.of(Role.ROLE_ADMINISTRATEUR));
    }


    private void createUserIfNotExists(String email, String password, String username, String firstname, String lastname, Set<Role> roles) {
        if (!userRepository.existsByUsername(username) && !userRepository.existsByEmail(email)) {

            User user = User.builder()
                    .email(email)
                    .username(username)
                    .password(passwordEncoder.encode(password))
                    .firstname(firstname)
                    .lastname(lastname)
                    .sexe("femme")
                    .telephone("51066141")
                    .address("ariana cv")
                    .enabled(true)
                    .dob(new Date(911260861000L))
                    .roles(roles)
                    .build();
            userRepository.save(user);
        }
    }
}
