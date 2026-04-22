package com.AuthSystem.AuthSystem.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "auth_tokens") // Maps to the SQL table we created
@Data
public class AuthToken {

    @Id
    private UUID tokenId; // Primary Key

    @Column(nullable = false)
    private String username; // Links the token to a user

    @Column(nullable = false)
    private LocalDateTime issuedAt = LocalDateTime.now(); // Audit field for when token was created

    @Column(nullable = false)
    private LocalDateTime expiryDate; // Used for session timeout logic
}