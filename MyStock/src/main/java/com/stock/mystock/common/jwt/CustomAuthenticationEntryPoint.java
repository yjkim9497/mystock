package com.stock.mystock.common.jwt;

import com.stock.mystock.common.response.ApiResponse;
import com.stock.mystock.common.response.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setContentType("application/json");
        response.setStatus(ErrorCode.AUTHENTICATION_ERROR.getStatus().value());
        ApiResponse<String> apiResponse = ApiResponse.error(ErrorCode.AUTHENTICATION_ERROR);
        response.getWriter().write(apiResponse.toJson()); // ApiResponse의 toJson() 메서드를 사용하여 JSON으로 변환
    }

}
