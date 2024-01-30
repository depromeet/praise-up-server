package com.praise.push.application.port.in.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserPostStateResponseDto {
    /**
     * 게시글 작성 가능 여부
     */
    private Boolean isCreatable;
}