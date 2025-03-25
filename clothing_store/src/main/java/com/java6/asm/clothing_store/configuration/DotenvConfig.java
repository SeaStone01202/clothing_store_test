package com.java6.asm.clothing_store.configuration;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DotenvConfig {

    @Bean
    public Dotenv dotenv() {
        Dotenv dotenv = Dotenv.configure()
                .filename("key.env") // Chỉ định tên file là key.env
                .directory("./") // Đường dẫn đến file (thư mục gốc của project)
                .ignoreIfMissing() // Bỏ qua nếu file không tồn tại
                .load();

        // Đưa tất cả biến môi trường từ file key.env vào System Environment
//        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

        return dotenv;
    }
}