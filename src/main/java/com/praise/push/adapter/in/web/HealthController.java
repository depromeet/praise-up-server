package com.praise.push.adapter.in.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HealthController {

    @GetMapping("/health")
    String checkHealth() {
        return "Server API Health GOOD";
    }
}
