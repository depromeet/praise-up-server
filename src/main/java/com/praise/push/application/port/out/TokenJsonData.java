package com.praise.push.application.port.out;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class TokenJsonData {
    private final WebClient webClient;
    private static final String TOKEN_URI = "https://kauth.kakao.com/oauth/token";
    private static final String REDIRECT_URL = "https://localhost:8080/oauth";
    private static final String GRANT_TYPE = "authorization_code";
    private static final String CLIENT_ID = "{secret.CLIENT_ID}";

    public TokenResponse getToken(String code) {
        String uri = TOKEN_URI + "?grant_type=" + GRANT_TYPE + "&client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URL + "&code=" + code;
        Flux<TokenResponse> response = webClient.post()
            .uri(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToFlux(TokenResponse.class);

        return response.blockFirst();
    }

}
