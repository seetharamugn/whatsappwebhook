package com.example.webhook.services.impl;

import com.example.webhook.model.JwtToken;
import com.example.webhook.repository.JwtTokenRepository;
import com.example.webhook.services.JwtTokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
public class JwtTokenServiceImpl implements JwtTokenService {

    @Value("${webhook.app.jwtCookieName}")
    private String jwtCookie;

    @Value("${webhook.app.jwtSecret}")
    private String jwtSecret;


    @Autowired
    private JwtTokenRepository jwtTokenRepository;

    @Override
    public ResponseCookie generateJwtCookie(UserDetailsImpl userPrincipal) {
        String jwt = generateTokenFromUsername(userPrincipal);
        ResponseCookie cookie = ResponseCookie.from(jwtCookie, jwt).path("/api").maxAge(24 * 60 * 60).httpOnly(true).build();
        return cookie;
    }
    @Override
    public JwtToken generateJwtToken(UserDetailsImpl userDetails) {

        Optional<JwtToken> token = jwtTokenRepository.findByUserId(userDetails.getId());
        if(token.isPresent()){
            JwtToken jwttoken = token.get();
            Instant now = Instant.now();
            if(jwttoken.getExpiryDate().isAfter(now)){
                return jwttoken;
            }else {
                jwtTokenRepository.delete(jwttoken);
            }
        }
        // ...
        JwtToken jwtToken = new JwtToken();
        Instant expiryDate = Instant.now().plus(Duration.ofHours(2));
        String jwt = this.generateTokenFromUsername(userDetails);
        jwtToken.setToken(jwt);
        jwtToken.setIssueDate(Instant.now());
        jwtToken.setExpiryDate(expiryDate);
        jwtToken.setUserId(userDetails.getId());
        jwtTokenRepository.save(jwtToken);
        // ...
        return jwtToken;
    }

    public String generateTokenFromUsername(UserDetailsImpl username) {
        JSONObject data =new JSONObject();
        data.put("username",username.getUsername());
        data.put("email",username.getEmail());
        return Jwts.builder()
                .setSubject(data.toString())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(Duration.ofHours(2))))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}
