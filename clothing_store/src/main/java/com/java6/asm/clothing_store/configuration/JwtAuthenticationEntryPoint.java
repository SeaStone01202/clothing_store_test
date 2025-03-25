package com.java6.asm.clothing_store.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java6.asm.clothing_store.dto.ApiResponse;
import com.java6.asm.clothing_store.exception.AppException;
import com.java6.asm.clothing_store.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String requestURI = request.getRequestURI();

        if (requestURI.contains("/auth/system/refresh")) {

        } else {

            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            ApiResponse<String> errorResponse = ApiResponse.error(
                    new AppException(ErrorCode.TOKEN_EXPIRED)
            );

            new ObjectMapper().writeValue(response.getOutputStream(), errorResponse);
        }
    }
}

