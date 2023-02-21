package com.example.webhook.repository;

import com.example.webhook.model.JwtToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface JwtTokenRepository extends MongoRepository<JwtToken, String> {

    Optional<JwtToken> findByToken(String token);

    void deleteByExpiryDateBefore(Instant expiryDate);

    void deleteByToken(String token);

    Optional<JwtToken> findByUserId(String user_id);
}
