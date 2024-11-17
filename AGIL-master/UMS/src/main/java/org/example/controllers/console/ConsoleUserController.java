package org.example.controllers.console;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.example.dtos.views.UserDto;
import org.example.enums.Role;
import org.example.payload.requests.CreateUserRequest;
import org.example.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("console/api/v1/users")
@RequiredArgsConstructor
public class ConsoleUserController {

    private final UserService userService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest createUserRequest) {
        return ResponseEntity.ok(userService.create(createUserRequest));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("/{userId}/role/{role}")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    public ResponseEntity<String> grantRoleToUser(@Parameter(name = "userId") @PathVariable("userId") Long userId, @PathVariable("role") Role role) {
        userService.grantRoleToUser(userId, role);
        return ResponseEntity.ok().body("Done");
    }

    @DeleteMapping("/{userId}/role/{role}")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    public ResponseEntity<String> revokeRoleFromUser(@Parameter(name = "userId") @PathVariable("userId") Long userId, @Parameter(name = "role") @PathVariable("role") Role role) {
        userService.revokeRoleFromUser(userId, role);
        return ResponseEntity.ok().body("Done");
    }

}
