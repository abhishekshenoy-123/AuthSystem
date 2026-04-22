package com.AuthSystem.AuthSystem.repository;

import com.AuthSystem.AuthSystem.model.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<AuthToken, UUID> {
    Optional<AuthToken> findByTokenId(UUID tokenId);
}