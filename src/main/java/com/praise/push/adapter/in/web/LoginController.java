package com.praise.push.adapter.in.web;

import com.praise.push.application.port.out.KakaoAccount;
import com.praise.push.application.port.out.KakaoInfo;
import com.praise.push.application.port.out.LoginResponse;
import com.praise.push.application.service.LoginService;
import com.praise.push.application.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/praise-up/api/v1")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Login", description = "Login/Logout API")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController  {
    private final LoginService loginService;
    private final UserService userService;

    @Operation(summary = "로그인")
    @ApiResponse(responseCode = "200", description = "로그인 성공")
    @GetMapping("/sign-up")
    public LoginResponse signUp(@RequestParam("code") String code) {
        KakaoInfo kakaoUserInfo = loginService.getAccessToken(code);
        KakaoAccount kakaoAccount = kakaoUserInfo.getKakaoAccount();
        return userService.doSocialLogin(kakaoAccount);
    }

    @Operation(summary = "로그아웃")
    @ApiResponse(responseCode = "200", description = "로그아웃")
    @GetMapping("/logout")
    public ResponseEntity<Void> logout() {
        loginService.logout();
        return ResponseEntity.noContent().build();
    }
}
