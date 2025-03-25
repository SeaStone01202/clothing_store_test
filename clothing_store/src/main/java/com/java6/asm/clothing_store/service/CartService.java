package com.java6.asm.clothing_store.service;

import com.java6.asm.clothing_store.dto.response.CartResponse;
import com.java6.asm.clothing_store.entity.Cart;

public interface CartService {

    CartResponse getCart(String userName);

}
