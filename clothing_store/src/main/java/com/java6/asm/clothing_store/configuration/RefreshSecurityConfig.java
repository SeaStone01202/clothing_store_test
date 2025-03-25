package com.java6.asm.clothing_store.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Order(1) // 🔥 Ưu tiên chạy trước `SecurityConfig`
public class RefreshSecurityConfig {

    private String[] urls = {
            "/auth/system/refresh",
            "/user/login",
            "/user/logout",
            "/user/register",
            "/product/list",
            "/product/**",
            "/product"
    };

    @Bean
    public SecurityFilterChain refreshSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher(urls) // ✅ Chỉ áp dụng cho `/auth/system/refresh`
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) // 🚀 Bỏ qua xác thực
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults());

        return http.build();
    }
}