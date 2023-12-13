package com.praise.push.user.domain.vo;

public enum OAuthProvider {
    KAKAO("kakao");

    OAuthProvider(String provider) {
        this.provider = provider;
    }

    private final String provider;

    public String getProvider() {
        return provider;
    }
}
