package com.praise.push.adapter.in.web;

import com.praise.push.application.port.in.CommentUseCase;
import com.praise.push.application.port.in.CreateCommentCommand;
import com.praise.push.application.port.in.dto.CommentResponseDto;
import com.praise.push.application.port.in.dto.CommentSimpleResponseDto;
import com.praise.push.common.model.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/praise-up/api/v1")
class CommentController {

    private final CommentUseCase commentUseCase;

    @PostMapping(value = "/posts/{postId}/comments", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<Void> createComment(
        @ModelAttribute CreateCommentCommand command,
        @PathVariable("postId") Long postId
    ) {
        commentUseCase.createComment(command, postId);

        return ResponseDto.created();
    }

    @GetMapping("/comments/{commentId}")
    ResponseEntity<?> getComments(@PathVariable("commentId") Long commentId) {
        CommentResponseDto comment = commentUseCase.getComment(commentId);

        return ResponseDto.ok(comment);
    }

    @DeleteMapping("/comments/{commentId}")
    ResponseEntity<Void> deleteComment(@PathVariable("commentId") Long commentId) {
        commentUseCase.deleteComment(commentId);

        return ResponseDto.noContent();
    }

    @GetMapping("/posts/{postId}/comments")
    ResponseEntity<Page<CommentResponseDto>> getComments(
        @PathVariable("postId") Long postId,
        @RequestParam(value = "page", defaultValue = "0") Integer page,
        @RequestParam(value = "size", defaultValue = "24") Integer size
    ) {
        Page<CommentResponseDto> comments = commentUseCase.getComments(postId, page, size);

        return ResponseDto.ok(comments);
    }
}
