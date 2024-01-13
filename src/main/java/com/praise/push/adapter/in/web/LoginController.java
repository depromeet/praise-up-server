package com.praise.push.adapter.in.web;

import com.praise.push.application.service.UserService;
import com.praise.push.application.port.out.KakaoAccount;
import com.praise.push.application.service.LoginService;
import com.praise.push.application.port.out.KakaoInfo;
import com.praise.push.application.port.out.LoginResponse;
import com.praise.push.application.port.out.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/praise-up/api/v1")
@RequiredArgsConstructor
@Slf4j
public class LoginController  {
    private final LoginService loginService;
    private final UserService userService;

    @GetMapping("/sign-up")
    public LoginResponse signUp(@RequestParam("code") String code) {
        KakaoInfo kakaoUserInfo = loginService.getAccessToken(code);
        KakaoAccount kakaoAccount = kakaoUserInfo.getKakaoAccount();

        return userService.doSocialLogin(kakaoAccount);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") Long id) {
        UserResponse response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }
}
