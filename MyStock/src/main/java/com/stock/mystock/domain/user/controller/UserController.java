package com.stock.mystock.domain.user.controller;

import com.stock.mystock.common.jwt.TokenProvider;
import com.stock.mystock.common.response.ApiResponse;
import com.stock.mystock.common.util.RedisUtil;
import com.stock.mystock.domain.user.dto.request.LoginRequestDto;
import com.stock.mystock.domain.user.dto.request.SignUpRequestDto;
import com.stock.mystock.domain.user.dto.response.LoginResponseDto;
import com.stock.mystock.domain.user.dto.response.UserResponseDto;
import com.stock.mystock.domain.user.repository.UserRepository;
import com.stock.mystock.domain.user.service.UserServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;
    private final UserRepository userRepository;
    private final RedisUtil redisUtil;
    private final TokenProvider tokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<UserResponseDto>> signup(@RequestBody SignUpRequestDto signUpRequestDto) {
        UserResponseDto userResponseDto = userService.signUp(signUpRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(userResponseDto));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDto>> login(@Valid @RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        LoginResponseDto loginResponseDto = userService.login(loginRequestDto, response);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(loginResponseDto));
    }
}
