package com.java6.asm.clothing_store.dto.response;

import com.java6.asm.clothing_store.constance.RoleEnum;
import com.java6.asm.clothing_store.constance.TypeAccountEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponse {

    private String email;

    private String fullname;

    private String image;

    private RoleEnum role;

    private TypeAccountEnum type;

}
