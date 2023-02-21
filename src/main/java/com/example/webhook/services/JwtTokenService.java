package com.example.webhook.services;

import com.example.webhook.model.JwtToken;
import com.example.webhook.services.impl.UserDetailsImpl;
import org.springframework.http.ResponseCookie;

public interface JwtTokenService {
    ResponseCookie generateJwtCookie(UserDetailsImpl userPrincipal);

    JwtToken generateJwtToken(UserDetailsImpl userDetails);

}
