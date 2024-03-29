package com.praise.push.adapter.in.web;

import com.praise.push.application.port.in.CommentUseCase;
import com.praise.push.application.port.in.CreateCommentCommand;
import com.praise.push.application.port.in.ReadCommentsQuery;
import com.praise.push.application.port.in.dto.CommentResponseDto;
import com.praise.push.common.model.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/praise-up/api/v1")
@Tag(name = "Comments", description = "Comment API")
@CrossOrigin(origins = "*", allowedHeaders = "*")
class CommentController {

    private final CommentUseCase commentUseCase;

    @Operation(summary = "댓글 등록")
    @ApiResponse(responseCode = "201", description = "댓글 등록 성공")
    @PostMapping(value = "/posts/{postId}/comments", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<Void> createComment(
        @Valid @ModelAttribute CreateCommentCommand command,
        @PathVariable("postId") Long postId
    ) {

        commentUseCase.createComment(command, postId);

        return ResponseDto.created();
    }

    @Operation(summary = "댓글 삭제")
    @ApiResponse(responseCode = "204", description = "댓글 삭제 성공")
    @DeleteMapping("/comments/{commentId}")
    ResponseEntity<Void> deleteComment(@PathVariable("commentId") Long commentId) {
        commentUseCase.deleteComment(commentId);

        return ResponseDto.noContent();
    }

    @Operation(summary = "댓글 목록 조회")
    @ApiResponse(responseCode = "200", description = "댓글 목록 조회 성공")
    @GetMapping("/posts/{postId}/comments")
    ResponseEntity<Page<CommentResponseDto>> getComments(
        @PathVariable("postId") Long postId,
        @Valid @ParameterObject @ModelAttribute ReadCommentsQuery readCommentsQuery
    ) {

        Page<CommentResponseDto> comments = commentUseCase.getComments(postId, readCommentsQuery);

        return ResponseDto.ok(comments);
    }
}
