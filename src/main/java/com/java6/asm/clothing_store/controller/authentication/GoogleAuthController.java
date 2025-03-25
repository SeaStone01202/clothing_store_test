package com.java6.asm.clothing_store.controller.authentication;

import com.java6.asm.clothing_store.dto.ApiResponse;
import com.java6.asm.clothing_store.dto.response.AuthResponse;
import com.java6.asm.clothing_store.dto.response.GoogleUserRegisterResponse;
import com.java6.asm.clothing_store.service.authentication.OAuthGoogleUserService;
import com.java6.asm.clothing_store.service.authentication.impl.JwtAccessRefreshTokenService;
import com.java6.asm.clothing_store.service.authentication.impl.JwtRefreshRefreshTokenService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/auth/google")
@RequiredArgsConstructor
public class GoogleAuthController {

    private final OAuthGoogleUserService oAuthGoogleUserService;

    @GetMapping("/success")
    public void googleLoginSuccess(@AuthenticationPrincipal OAuth2User oauth2User, HttpServletResponse response) throws IOException {
        oAuthGoogleUserService.authenticateAndGenerateTokens(oauth2User, null, response);
        response.sendRedirect("http://localhost:5173/callback"); // Không gửi accessToken qua query
    }
}
