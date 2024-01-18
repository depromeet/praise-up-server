package com.praise.push.application.port.out;


import io.swagger.v3.oas.annotations.media.Schema;

public record LoginResponse(
    @Schema(description = "유저 아이디", example = "1")
    Long userId,
    @Schema(description = "첫 회원가입 여부", example = "true")
    Boolean isSigned
) {
    public LoginResponse(Long userId, Boolean isSigned) {
        this.userId = userId;
        this.isSigned = isSigned;
    }
}