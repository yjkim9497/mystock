package com.stock.mystock.domain.user.dto.response;

import lombok.Builder;

@Builder
public record TokenDto(String grantType,
                       String accessToken,
                       String refreshToken,
                       Long refreshTokenExpiresIn) {
}
