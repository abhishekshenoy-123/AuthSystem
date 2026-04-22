package com.AuthSystem.AuthSystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.AuthSystem.AuthSystem.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        String token = authService.login(username, password);
        return token != null ? ResponseEntity.ok(token) : ResponseEntity.status(401).body("Invalid Credentials");
    }

    @GetMapping("/secure-data")
    public ResponseEntity<String> getSecureData(@RequestHeader("Authorization") String token) {
        if (authService.validateToken(token)) {
            return ResponseEntity.ok("Welcome! You have accessed secure data using your token.");
        }
        return ResponseEntity.status(403).body("Access Denied: Invalid Token");
    }
}