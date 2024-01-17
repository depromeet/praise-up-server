package com.praise.push.application.port.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentCommand {
    @NotBlank(message = "nickname must not be blank.")
    @Size(min = 1, max = 4, message = "nickname length must be 1 to 4.")
    private String nickname;

    @Size(max = 40, message = "content length is up to 40.")
    private String content;

    private MultipartFile image;
}
