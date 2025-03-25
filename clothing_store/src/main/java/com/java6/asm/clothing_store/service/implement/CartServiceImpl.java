package com.java6.asm.clothing_store.service.implement;

import com.java6.asm.clothing_store.constance.StatusEnum;
import com.java6.asm.clothing_store.dto.mapper.CartMapper;
import com.java6.asm.clothing_store.dto.response.CartResponse;
import com.java6.asm.clothing_store.dto.response.UserResponse;
import com.java6.asm.clothing_store.entity.Cart;
import com.java6.asm.clothing_store.entity.User;
import com.java6.asm.clothing_store.exception.AppException;
import com.java6.asm.clothing_store.exception.ErrorCode;
import com.java6.asm.clothing_store.repository.CartRepository;
import com.java6.asm.clothing_store.repository.UserRepository;
import com.java6.asm.clothing_store.service.CartService;
import com.java6.asm.clothing_store.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;

    private UserService userService;

    private final UserRepository userRepository;

    private final CartMapper cartMapper;

    @Override
    @Transactional
    public CartResponse getCart(String email) {
        User user = userRepository.findByEmailAndStatus(email, StatusEnum.ACTIVE)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        // Kiểm tra xem user đã có giỏ hàng chưa
        Optional<Cart> existingCart = cartRepository.findByUser(user);
        if (existingCart.isPresent()) {
            return cartMapper.toResponse(existingCart.get());
        }

        // Nếu không có giỏ hàng, tạo mới
        Cart newCart = new Cart();
        newCart.setUser(user);
        Cart savedCart = cartRepository.save(newCart);

        return cartMapper.toResponse(savedCart);
    }

}
