package com.praise.push.post.application.port.in;

import com.praise.push.post.domain.Keyword;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class CreatePostCommand {
    @NotNull
    private final Long postId;

    @NotNull
    private final String title;

    @NotNull
    private final String content;

    @NotNull
    private final String imageUrl;

    @NotNull
    private final Keyword keyword;
}
