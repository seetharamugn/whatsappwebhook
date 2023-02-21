package com.example.webhook.payload.response;

import com.example.webhook.model.JwtToken;

import java.util.List;

public class UserInfoResponse {
  private String id;
  private String username;
  private String email;
  private List<String> roles;

  private JwtToken jwtToken;

  public UserInfoResponse(JwtToken token, String id, String username, String email, List<String> roles) {
    this.jwtToken=token;
    this.id = id;
    this.username = username;
    this.email = email;
    this.roles = roles;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<String> getRoles() {
    return roles;
  }

  public JwtToken getJwtToken() {
    return jwtToken;
  }

  public void setJwtToken(JwtToken jwtToken) {
    this.jwtToken = jwtToken;
  }
}
