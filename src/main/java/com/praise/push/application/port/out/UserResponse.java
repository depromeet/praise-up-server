package com.praise.push.application.port.out;

import com.praise.push.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;

public record UserResponse(
    @Schema(description = "유저 아이디", example = "1")
    Long userId,
    @Schema(description = "유저 이메일", example = "praise-up@gmail.com")
    String email,
    @Schema(description = "유저 닉네임", example = "User1")
    String nickname,
    @Schema(description = "유저 프로필 이미지")
    String profileImage
) {
    public UserResponse(User user) {
        this(
            user.getId(),
            user.getEmail(),
            user.getNickname(),
            user.getProfileImage()
        );
    }
}
