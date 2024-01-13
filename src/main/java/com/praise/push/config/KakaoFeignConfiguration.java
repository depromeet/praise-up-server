package com.praise.push.config;


import feign.Client;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KakaoFeignConfiguration {

    @Bean
    public Client feignClient() {
        return new Client.Default(null, null);
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
