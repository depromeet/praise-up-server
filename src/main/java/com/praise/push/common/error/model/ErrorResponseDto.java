package com.praise.push.common.error.model;

import com.praise.push.common.ErrorCode;
import lombok.Builder;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Builder
public record ErrorResponseDto(
        LocalDateTime timestamp,
        Integer status,
        String code,
        String message
) {

    /**
     * returns error response with ErrorCode
     */
    public static ResponseEntity<ErrorResponseDto> build(ErrorCode errorCode) {
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now())
                .status(errorCode.getHttpStatus().value())
                .code(errorCode.getErrorCode())
                .message(errorCode.getErrorMessage())
                .build();

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(errorResponseDto);
    }

    /**
     * returns error response with ErrorCode and custom error message
     */
    public static ResponseEntity<ErrorResponseDto> build(ErrorCode errorCode, String message) {
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now())
                .status(errorCode.getHttpStatus().value())
                .code(errorCode.getErrorCode())
                .message(message)
                .build();

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(errorResponseDto);
    }
}
