package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.daos.entities.User;
import org.example.daos.repositories.UserRepository;
import org.example.exceptions.IllegalAccessException;
import org.example.exceptions.UnauthorizedException;
import org.example.payload.requests.LoginRequest;
import org.example.payload.requests.RefreshRequest;
import org.example.payload.responses.AuthResponse;
import org.example.utils.JwtUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        User user = userRepository.findUserByUsername(loginRequest.getUsername()).orElseThrow(()-> new BadCredentialsException("Invalid username or password"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        if (!user.isEnabled()) {
            throw new IllegalAccessException("User does not have access to the plateform!");
        }

        String accessToken = jwtUtils.generateJwt(user.getUsername());
        String refreshToken = jwtUtils.generateRefreshTokenFromPseudo(user.getUsername());

        return new AuthResponse(accessToken, refreshToken);
    }

    @Override
    public AuthResponse refresh(RefreshRequest refreshRequest) {

        String refreshToken = refreshRequest.getRefresh_token();

        if (!jwtUtils.isRefreshToken(refreshToken)) {
            throw new UnauthorizedException("Invalid token type. Expected a refresh token.");
        }

        String username = jwtUtils.getUsernameFromJwtToken(refreshToken);

        User user = userRepository.findUserByUsername(username).orElseThrow(()-> new BadCredentialsException("Invalid username or password"));

        if (!user.isEnabled()) {
            throw new IllegalAccessException("User does not have access to the plateform!");
        }

        String newAccessToken = jwtUtils.generateJwt(username);
        String newRefreshToken = jwtUtils.generateRefreshTokenFromPseudo(username);

        return new AuthResponse(newAccessToken, newRefreshToken);
    }
}
