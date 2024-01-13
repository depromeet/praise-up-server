package com.praise.push.adapter.in.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/praise-up/api/v1")
@Slf4j
public class IndexController {

    @GetMapping("/index")
    public String index() {
        return "loginForm";
    }

    @GetMapping("/logout-form")
    public String logout() {
        return "logoutForm";
    }
}
