package com.praise.push.adapter.in.web.client;

import com.praise.push.application.port.out.KakaoInfo;
import com.praise.push.application.port.out.KakaoToken;
import com.praise.push.config.KakaoFeignConfiguration;
import java.net.URI;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "kakaoClient", configuration = KakaoFeignConfiguration.class)
@Component
public interface KakaoClient {

    public static final String APPLICATION_FORM_URLENCODED_UTF8_VALUE =
        MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=utf-8";

    @PostMapping(consumes = APPLICATION_FORM_URLENCODED_UTF8_VALUE)
    KakaoInfo getUserInfo(URI baseUrl, @RequestHeader("Authorization") String accessToken);

    @PostMapping(consumes = APPLICATION_FORM_URLENCODED_UTF8_VALUE)
    KakaoToken getToken(URI baseUrl, @RequestParam("client_id") String restApiKey,
                        @RequestParam("redirect_uri") String redirectUrl,
                        @RequestParam("code") String code,
                        @RequestParam("grant_type") String grantType);
}
