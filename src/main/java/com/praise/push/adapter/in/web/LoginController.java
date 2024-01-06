package com.praise.push.adapter.in.web;

import com.praise.push.application.port.out.TokenJsonData;
import com.praise.push.application.port.out.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/praise-up/api/v1")
@RequiredArgsConstructor
@Slf4j
public class LoginController  {
    private final TokenJsonData tokenJsonData;
    private final UserInfo userInfo;
    private final UserService userService;

    @GetMapping("/sign-up")
    public String signUp(@RequestParam("coce") String code) {
        log.info("code : {}", code);
    }

}
