package com.stock.mystock.domain.user.service;

import com.stock.mystock.domain.user.dto.request.LoginRequestDto;
import com.stock.mystock.domain.user.dto.request.SignUpRequestDto;
import com.stock.mystock.domain.user.dto.response.LoginResponseDto;
import com.stock.mystock.domain.user.dto.response.UserResponseDto;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

public interface UserService {
    UserResponseDto signUp(@Valid final SignUpRequestDto requestDto);
    LoginResponseDto login(@Valid final LoginRequestDto requestDto, HttpServletResponse response);

}
