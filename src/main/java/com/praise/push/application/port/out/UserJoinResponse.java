package com.praise.push.application.port.out;

import com.praise.push.user.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;



public record UserJoinResponse(
    @Schema(description = "유저 아이디", example = "1")
    Long userId
) {
    public UserJoinResponse(User user) {
        this(user.getId());
    }
}
