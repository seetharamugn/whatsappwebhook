package com.example.webhook.services;

import com.example.webhook.model.JwtToken;
import org.springframework.http.ResponseCookie;

public interface JwtTokenService {
    ResponseCookie generateJwtCookie(UserDetailsImpl userPrincipal);

    JwtToken generateJwtToken(UserDetailsImpl userDetails);

    void deleteExpiredTokens();

    void deleteToken(String token);
}
