package org.example.controllers.pub;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.example.dtos.views.UserDto;
import org.example.enums.Role;
import org.example.payload.requests.CreateUserRequest;
import org.example.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATEUR')")
    public ResponseEntity<UserDto> me(Authentication authentication) {
        return ResponseEntity.ok(userService.findByUsername(authentication.getName()));
    }

}
