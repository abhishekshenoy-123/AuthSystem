package com.AuthSystem.AuthSystem.service;

import com.AuthSystem.AuthSystem.model.AuthToken;
import com.AuthSystem.AuthSystem.repository.TokenRepository;
import com.AuthSystem.AuthSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository; // Inject the new repository

    public String login(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(user -> user.getPassword().equals(password))
                .map(user -> {
                    // Generate the token
                    AuthToken authToken = new AuthToken();
                    authToken.setTokenId(UUID.randomUUID());
                    authToken.setUsername(username);
                    authToken.setExpiryDate(LocalDateTime.now().plusHours(2));

                    // SAVE to the auth_tokens table
                    tokenRepository.save(authToken);

                    return authToken.getTokenId().toString();
                }).orElse(null);
    }

    public boolean validateToken(String tokenStr) {
        try {
            UUID tokenId = UUID.fromString(tokenStr);
            // CHECK the auth_tokens table
            return tokenRepository.findById(tokenId)
                    .map(token -> token.getExpiryDate().isAfter(LocalDateTime.now()))
                    .orElse(false);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}