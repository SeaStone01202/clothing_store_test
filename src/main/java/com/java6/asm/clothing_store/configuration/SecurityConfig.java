package com.java6.asm.clothing_store.configuration;

import com.java6.asm.clothing_store.utils.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@Order(2) // 🚀 Xử lý sau cấu hình dành cho refresh token
@RequiredArgsConstructor
public class SecurityConfig {


    private final String[] PUBLIC_URLS = {
            "/auth/system/login",
            "/auth/system/register",
            "/auth/google/*",
            "/auth/zalo/*",
    };


    /**
     * ✅ Cấu hình bảo mật cho ứng dụng Spring Security
     * - Ngăn chặn truy cập trái phép vào các API
     * - Bật xác thực bằng JWT cho các request
     * 🔹 Cấu hình cụ thể:
     * - Cho phép truy cập công khai vào các endpoint `/auth/login`, `/auth/refresh`, `/auth/logout`
     * - Mọi request khác đều yêu cầu xác thực
     * - Sử dụng OAuth2 Resource Server với JWT để xác thực người dùng
     * @param http Đối tượng cấu hình bảo mật của Spring Security
     * @return SecurityFilterChain - Chuỗi bộ lọc bảo mật đã được thiết lập
     * @throws Exception Nếu có lỗi trong quá trình cấu hình bảo mật
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/auth/system/login",
                                "/auth/system/logout"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                )
                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("/auth/google/success", true)
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(Customizer.withDefaults())
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                )
        ;

        return http.build();
    }




    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }



    @Bean
    public PageUtil pageUtil() {
        return new PageUtil();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}