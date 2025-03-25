package com.java6.asm.clothing_store.dto.mapper;

import com.java6.asm.clothing_store.dto.response.CartResponse;
import com.java6.asm.clothing_store.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { CartDetailMapper.class })
public interface CartMapper {

    @Mapping(target = "userId", source = "user.id")
    CartResponse toResponse(Cart cart);

    @Mapping(target = "user", ignore = true)
    Cart toEntity(CartResponse cartResponse);
}