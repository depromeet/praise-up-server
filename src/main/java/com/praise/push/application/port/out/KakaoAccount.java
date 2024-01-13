package com.praise.push.application.port.out;

import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
public class KakaoAccount {
    private Profile profile;
    private String gender;
    private String birthday;
    private String email;
}
