package com.java6.asm.clothing_store.service.authentication.impl;

import com.java6.asm.clothing_store.exception.AppException;
import com.java6.asm.clothing_store.exception.ErrorCode;
import com.java6.asm.clothing_store.service.authentication.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class JwtRefreshRefreshTokenService implements RefreshTokenService {

    private final RedisTemplate<String, String> redisTemplate;

    private static final long REFRESH_TOKEN_TTL = 7 * 24 * 60 * 60; // ⏳ TTL: 7 ngày

    @Override
    public String generateToken(String email, String deviceId) {
        // 📌 Kiểm tra xem deviceId đã tồn tại chưa
        String redisKey = "refreshToken:" + email + ":" + deviceId;

        String existingToken = redisTemplate.opsForValue().get(redisKey);

        if (existingToken != null) {
            return existingToken; // ✅ Trả về refresh token cũ nếu tồn tại
        }

        String refreshToken = UUID.randomUUID().toString();

        redisTemplate.opsForValue().set(redisKey, refreshToken, REFRESH_TOKEN_TTL, TimeUnit.SECONDS);

        return refreshToken;
    }

    @Override
    public String validateToken(String refreshToken) {

        Set<String> keys = redisTemplate.keys("refreshToken:*");

        for (String key : keys) {
            String storedToken = redisTemplate.opsForValue().get(key);
            if (refreshToken.equals(storedToken)) {
                return key.split(":")[1]; // ✅ Trả về email nếu token hợp lệ
            }
        }

        throw new AppException(ErrorCode.REFRESH_TOKEN_INVALID);
    }

    @Override
    public boolean deleteToken(String refreshToken) {

        Set<String> keys = redisTemplate.keys("refreshToken:*");

        if (refreshToken == null || refreshToken.isEmpty()) {
            throw new AppException(ErrorCode.TOKEN_MISSING);
        }

        for (String key : keys) {
            String storedToken = redisTemplate.opsForValue().get(key);
            if (refreshToken.equals(storedToken)) {
                redisTemplate.delete(key);
                return true;
            }
        }

        throw new AppException(ErrorCode.REFRESH_TOKEN_INVALID);
    }

    @Override
    public int countDevices(String email) {
        Set<String> existingDevices = redisTemplate.keys("refreshToken:" + email + ":*");
        return existingDevices.size();
    }

    @Override
    public boolean existsTokenForDevice(String email, String deviceId) {
        String redisKey = "refreshToken:" + email + ":" + deviceId;
        return redisTemplate.hasKey(redisKey);
    }

    @Override
    public String getTokenForDevice(String email, String deviceId) {
        String redisKey = "refreshToken:" + email + ":" + deviceId;
        return redisTemplate.opsForValue().get(redisKey);
    }
}
