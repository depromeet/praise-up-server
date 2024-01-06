package com.praise.push.application.port.out;

import lombok.Data;

@Data
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
    private Integer expires;
    private String scope;
    private Integer refreshTokenExpires;
}
