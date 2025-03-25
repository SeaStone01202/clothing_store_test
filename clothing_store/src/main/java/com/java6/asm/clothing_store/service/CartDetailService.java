package com.java6.asm.clothing_store.service;

import com.java6.asm.clothing_store.dto.response.CartDetailResponse;
import com.java6.asm.clothing_store.entity.CartDetail;

public interface CartDetailService {

    CartDetailResponse addProductToCart(String email, Integer productId, Integer quantity);

    void deleteCartDetail(Long cartDetailId);

    CartDetailResponse updateQuantity(Long cartDetailId, Integer quantity);
}
