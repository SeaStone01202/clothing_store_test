package com.java6.asm.clothing_store.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {

    private Integer id;

    private String name;

    private String description;

    private String imageUrl;

    private Integer stock;

    private Double price;

    private String category;
}
