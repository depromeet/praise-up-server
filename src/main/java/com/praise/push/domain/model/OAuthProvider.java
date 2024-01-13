package com.praise.push.domain.model;

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
