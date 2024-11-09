package com.stock.mystock.common.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.mystock.common.response.ApiResponse;
import com.stock.mystock.common.response.ErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.error("[AccessDeniedHandler] - 해당 엔드포인트에 접근할 권한 없음: {}, {}", request.getUserPrincipal().getName(), request.getServletPath());
        ObjectMapper objectMapper = new ObjectMapper();

        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(ApiResponse.error(ErrorCode.PERMISSION_DENIED)));

    }
}
