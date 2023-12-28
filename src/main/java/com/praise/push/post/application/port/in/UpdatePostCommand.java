package com.praise.push.post.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePostCommand {
    private String title;
    private String content;
    private String imageUrl;
    private String keyword;
}
