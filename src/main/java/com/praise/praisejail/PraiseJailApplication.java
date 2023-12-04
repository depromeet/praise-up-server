package com.praise.praisejail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PraiseJailApplication {

    public static void main(String[] args) {
        SpringApplication.run(PraiseJailApplication.class, args);
    }

}
