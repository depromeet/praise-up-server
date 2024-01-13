package com.praise.push.application;

import com.praise.push.adapter.in.web.client.KakaoClient;
import com.praise.push.application.port.out.KakaoInfo;
import com.praise.push.application.port.out.KakaoToken;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final KakaoClient client;

    @Value("${kakao.auth-url}")
    private String kakaoAuthUrl;

    @Value("${kakao.user-api-url}")
    private String kakaoUserApiUrl;

    @Value("${kakao.restapi-key}")
    private String restapiKey;

    @Value("${kakao.redirect-url}")
    private String redirectUrl;


    public KakaoInfo getAccessToken(String code) {
        final KakaoToken token = getToken(code);
        log.info("token: {}", token);
        try {
            return client.getUserInfo(new URI(kakaoUserApiUrl), token.getTokenType() + " " + token.getAccessToken());
        } catch (Exception e) {
            log.error("get Info error", e);
            return KakaoInfo.fail();
        }
    }

    private KakaoToken getToken(final String code) {
        try {
            URI baseUrl = new URI(kakaoAuthUrl);
            return client.getToken(baseUrl, restapiKey, redirectUrl, code, "authorization_code");
        } catch (Exception e) {
            log.error("Login get Token error", e);
            return KakaoToken.fail();
        }
    }
}
