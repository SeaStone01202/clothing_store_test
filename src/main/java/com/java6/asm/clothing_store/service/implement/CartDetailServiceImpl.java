package com.java6.asm.clothing_store.service.implement;

import com.java6.asm.clothing_store.dto.mapper.CartDetailMapper;
import com.java6.asm.clothing_store.dto.mapper.CartMapper;
import com.java6.asm.clothing_store.dto.response.CartDetailResponse;
import com.java6.asm.clothing_store.dto.response.CartResponse;
import com.java6.asm.clothing_store.entity.Cart;
import com.java6.asm.clothing_store.entity.CartDetail;
import com.java6.asm.clothing_store.entity.Product;
import com.java6.asm.clothing_store.exception.AppException;
import com.java6.asm.clothing_store.exception.ErrorCode;
import com.java6.asm.clothing_store.repository.CartDetailRepository;
import com.java6.asm.clothing_store.repository.CartRepository;
import com.java6.asm.clothing_store.repository.ProductRepository;
import com.java6.asm.clothing_store.service.CartDetailService;
import com.java6.asm.clothing_store.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CartDetailServiceImpl implements CartDetailService {

    private final CartDetailRepository cartDetailRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartService cartService;
    private final CartMapper cartMapper;
    private final CartDetailMapper cartDetailMapper;

    @Override
    @Transactional
    public CartDetailResponse addProductToCart(String email, Integer productId, Integer quantity) {
        CartResponse cartResponse = cartService.getCart(email);
        if (cartResponse == null) {
            throw new AppException(ErrorCode.CART_NOT_FOUND);
        }

        Cart cart = cartMapper.toEntity(cartResponse);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        CartDetail existingCartDetail = cartDetailRepository.findByCartAndProduct(cart, product)
                .orElse(null);

        CartDetail cartDetail;
        if (existingCartDetail != null) {
            existingCartDetail.setQuantity(existingCartDetail.getQuantity() + quantity);
            cartDetail = cartDetailRepository.save(existingCartDetail);
        } else {
            cartDetail = new CartDetail();
            cartDetail.setCart(cart);
            cartDetail.setProduct(product);
            cartDetail.setQuantity(quantity);
            cartDetail = cartDetailRepository.save(cartDetail);
        }

        return cartDetailMapper.toResponse(cartDetail);
    }

    @Override
    @Transactional
    public CartDetailResponse updateQuantity(Long cartDetailId, Integer quantity) {
        if (quantity <= 0) {
            throw new AppException(ErrorCode.INVALID_QUANTITY);
        }

        CartDetail cartDetail = cartDetailRepository.findById(cartDetailId)
                .orElseThrow(() -> new AppException(ErrorCode.CART_ITEM_NOT_FOUND));

        cartDetail.setQuantity(quantity);
        cartDetail = cartDetailRepository.save(cartDetail);

        return cartDetailMapper.toResponse(cartDetail);
    }

    @Override
    @Transactional
    public void deleteCartDetail(Long cartDetailId) {
        CartDetail cartDetail = cartDetailRepository.findById(cartDetailId)
                .orElseThrow(() -> new AppException(ErrorCode.CART_ITEM_NOT_FOUND));
        cartDetailRepository.delete(cartDetail);
    }
}