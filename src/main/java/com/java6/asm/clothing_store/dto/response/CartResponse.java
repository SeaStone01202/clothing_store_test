package com.java6.asm.clothing_store.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class CartResponse {
    private Integer id;
    private Integer userId; // Làm phẳng: chỉ lấy userId thay vì UserResponse
    private List<CartDetailResponse> cartDetails; // Giữ danh sách CartDetailResponse, nhưng CartDetailResponse sẽ được làm phẳng
}