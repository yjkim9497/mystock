package com.stock.mystock.domain.user.service;

import com.stock.mystock.common.exception.CustomException;
import com.stock.mystock.common.jwt.TokenProvider;
import com.stock.mystock.common.response.ErrorCode;
import com.stock.mystock.common.util.RedisUtil;
import com.stock.mystock.domain.user.dto.request.LoginRequestDto;
import com.stock.mystock.domain.user.dto.request.SignUpRequestDto;
import com.stock.mystock.domain.user.dto.response.LoginResponseDto;
import com.stock.mystock.domain.user.dto.response.TokenDto;
import com.stock.mystock.domain.user.dto.response.UserResponseDto;
import com.stock.mystock.domain.user.entity.User;
import com.stock.mystock.domain.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManagerBuilder managerBuilder;
    private final TokenProvider tokenProvider;
    private final RedisUtil redisUtil;

    @Override
    @Transactional
    public UserResponseDto signUp(SignUpRequestDto requestDto) {
        String encodedPassword = bCryptPasswordEncoder.encode(requestDto.getPassword());
        User user = User.builder()
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .password(encodedPassword)
                .point(100000L)
                .build();

        User savedUser = userRepository.save(user);

        UserResponseDto responseDto = UserResponseDto.builder()
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .name(savedUser.getName())
                .point(savedUser.getPoint())
                .build();

        return responseDto;
    }

    @Override
    @Transactional
    public LoginResponseDto login(LoginRequestDto requestDto, HttpServletResponse response) {
        User user = userRepository.findByEmail(requestDto.getEmail()).orElseThrow(
                ()-> new CustomException(ErrorCode.USER_NOT_FOUND)
        );

        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(String.valueOf(user.getId()), requestDto.getPassword());

        Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);

        TokenDto tokenDto = tokenProvider.generateToken(authentication);
        tokenToHeader(tokenDto, response);

        redisUtil.setData(String.valueOf(user.getId()), tokenDto.refreshToken(), tokenDto.refreshTokenExpiresIn());
        LoginResponseDto responseDto = LoginResponseDto.builder()
                .id(user.getId())
                .build();

        return responseDto;

    }

    private void tokenToHeader(TokenDto tokenDto, HttpServletResponse response) {
        response.addHeader("Authorization", "Bearer " + tokenDto.refreshToken());
        response.addHeader("refreshToken", tokenDto.refreshToken());
    }
}
