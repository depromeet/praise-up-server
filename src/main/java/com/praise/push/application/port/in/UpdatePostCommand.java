package com.praise.push.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePostCommand {
    private String content;
    private String imageUrl;
    private String keyword;
}
