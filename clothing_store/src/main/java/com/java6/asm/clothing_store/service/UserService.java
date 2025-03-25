package com.java6.asm.clothing_store.service;

import com.java6.asm.clothing_store.dto.request.UserRegisterRequest;
import com.java6.asm.clothing_store.dto.request.UserRequest;
import com.java6.asm.clothing_store.dto.response.SystemUserRegisterResponse;
import com.java6.asm.clothing_store.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRegisterRequest request);

    UserResponse updateUser(UserRequest request);

    void deleteUser(Integer userId);

    List<UserResponse> retrieveAllUsers();

    UserResponse retrieveUserByEmail(String  email);

}
