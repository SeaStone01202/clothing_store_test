package com.java6.asm.clothing_store.configuration;

import com.java6.asm.clothing_store.constance.RoleEnum;
import com.java6.asm.clothing_store.constance.StatusEnum;
import com.java6.asm.clothing_store.constance.TypeAccountEnum;
import com.java6.asm.clothing_store.entity.User;
import com.java6.asm.clothing_store.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration class to initialize the application with a default admin user.
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class ApplicationInitConfig {

    private static final String INITIALIZING_MESSAGE = "Initializing application.....";
    private static final String INITIALIZATION_COMPLETED_MESSAGE = "Application initialization completed .....";
    private static final String ADMIN_ADDED_MESSAGE_TEMPLATE = "Admin user added with email: %s and default password: %s. Please change!";

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.password}")
    private String adminPassword;

    /**
     * Creates an ApplicationRunner to initialize the default admin user.
     *
     * @param userRepository The repository to interact with User entities
     * @return ApplicationRunner to execute initialization logic
     */
    @Bean
    ApplicationRunner applicationRunner() {
        log.info(INITIALIZING_MESSAGE);
        return args -> {
            if (userRepository.findByEmailAndStatus(adminEmail, StatusEnum.ACTIVE).isEmpty()) {
                User adminUser = buildAdminUser();
                userRepository.save(adminUser);
                log.warn(String.format(ADMIN_ADDED_MESSAGE_TEMPLATE, adminEmail, adminPassword));
            }
            log.info(INITIALIZATION_COMPLETED_MESSAGE);
        };
    }

    /**
     * Builds a default admin user with predefined properties.
     *
     * @return The admin User entity
     */
    private User buildAdminUser() {
        return User.builder()
                .email(adminEmail)
                .password(passwordEncoder.encode(adminPassword))
                .image("https://th.bing.com/th/id/OIP.T3hXIZf46Yfv56sRAEtZHQHaJ4?rs=1&pid=ImgDetMain")
                .role(RoleEnum.ADMIN)
                .status(StatusEnum.ACTIVE)
                .type(TypeAccountEnum.SYSTEM)
                .build();
    }
}