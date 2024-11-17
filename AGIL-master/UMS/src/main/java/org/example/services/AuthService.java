package org.example.services;

import org.example.payload.requests.LoginRequest;
import org.example.payload.requests.RefreshRequest;
import org.example.payload.responses.AuthResponse;

public interface AuthService {

    AuthResponse login(LoginRequest loginRequest);

    AuthResponse refresh(RefreshRequest refreshRequest);

}
