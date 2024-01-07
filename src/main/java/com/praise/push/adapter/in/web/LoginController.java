package com.praise.push.adapter.in.web;

import com.praise.push.application.port.out.KakaoAccount;
import com.praise.push.application.port.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController  {
    private final LoginService loginService;

    @GetMapping("/sign-up")
    public KakaoAccount signUp(@RequestParam("code") String code) {
        log.info("code: {}", code);
        KakaoAccount kakaoAccount = loginService.getAccessToken(code).getKakaoAccount();
        log.info("KakaoAccount: {}", kakaoAccount);
        return kakaoAccount;
    }
}
