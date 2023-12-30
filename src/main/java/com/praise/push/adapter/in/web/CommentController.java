package com.praise.push.adapter.in.web;

import com.praise.push.application.port.in.CommentUseCase;
import com.praise.push.application.port.in.CreateCommentCommand;
import com.praise.push.application.port.in.dto.CommentSimpleResponseDto;
import com.praise.push.common.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

        return ResponseDto.noContent(); // TODO: replace created(201) with Empty Body
    }

    @DeleteMapping("/comments/{commentId}")
    ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentUseCase.deleteComment(commentId);

        return ResponseDto.noContent();
    }

    @GetMapping("/posts/{postId}/comments")
    ResponseEntity<Page<CommentSimpleResponseDto>> getComments(
        @PathVariable Long postId,
        @RequestParam(value = "page", defaultValue = "0") Integer page,
        @RequestParam(value = "size", defaultValue = "24") Integer size
    ) {
        Page<CommentSimpleResponseDto> comments = commentUseCase.getComments(postId, page, size);

        return ResponseDto.ok(comments);
    }
}
