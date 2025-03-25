package com.java6.asm.clothing_store.service.authentication;

public interface AccessTokenService {

    String generateToken(String username);

    String validateToken(String token);
}