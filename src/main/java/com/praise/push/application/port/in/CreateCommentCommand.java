package com.praise.push.application.port.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public record CreateCommentCommand(
        @NotBlank(message = "nickname must not be blank.")
        @Size(min = 1, max = 4, message = "nickname length must be 1 to 4.")
        String nickname,
        @Size(max = 40, message = "content length is up to 40.")
        String content,
        MultipartFile image
) {
}
