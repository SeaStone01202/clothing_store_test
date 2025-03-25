package com.java6.asm.clothing_store.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CartDetailResponse {
    private Integer id;
    private Integer quantity;
    private LocalDate createdAt;

    // Làm phẳng các trường của Product
    private Integer productId;
    private String productName;
    private String productDescription;
    private String productImageUrl;
    private Integer productStock;
    private Double productPrice;
    private String productCategory; // Làm phẳng: chỉ lấy tên category
}