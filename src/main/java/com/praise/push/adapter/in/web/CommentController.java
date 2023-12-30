package com.praise.push.adapter.in.web;

import com.praise.push.application.port.in.CommentUseCase;
import com.praise.push.application.port.in.CreateCommentCommand;
import com.praise.push.common.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/praise-up/api/v1")
class CommentController {

    private final CommentUseCase commentUseCase;

    @PostMapping("/posts/{postId}/comments")
    ResponseEntity<Void> createComment(
        // TODO: uploaded image file
        @RequestBody CreateCommentCommand command,
        @PathVariable Long postId
    ) {
        commentUseCase.createComment(command, postId);

        return ResponseDto.noContent();
    }
}
