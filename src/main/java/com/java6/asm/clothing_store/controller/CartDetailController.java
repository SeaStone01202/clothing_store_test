package com.java6.asm.clothing_store.controller;

import com.java6.asm.clothing_store.dto.ApiResponse;
import com.java6.asm.clothing_store.dto.response.CartDetailResponse;
import com.java6.asm.clothing_store.entity.CartDetail;
import com.java6.asm.clothing_store.service.CartDetailService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart/details")
@AllArgsConstructor
public class CartDetailController {

    private final CartDetailService cartDetailService;

    @PostMapping
    public ResponseEntity<ApiResponse<CartDetailResponse>> addProductToCart(
            @RequestParam String email,
            @RequestParam Integer productId,
            @RequestParam Integer quantity) {
        CartDetailResponse cartDetail = cartDetailService.addProductToCart(email, productId, quantity);
        return ResponseEntity.ok(ApiResponse.success(cartDetail));
    }

    @PutMapping("/{cartDetailId}")
    public ResponseEntity<ApiResponse<CartDetailResponse>> updateQuantity(
            @PathVariable Long cartDetailId,
            @RequestParam Integer quantity) {
        CartDetailResponse response = cartDetailService.updateQuantity(cartDetailId, quantity);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @DeleteMapping("/{cartDetailId}")
    public ResponseEntity<ApiResponse<Void>> deleteCartDetail(@PathVariable Long cartDetailId) {
        cartDetailService.deleteCartDetail(cartDetailId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}