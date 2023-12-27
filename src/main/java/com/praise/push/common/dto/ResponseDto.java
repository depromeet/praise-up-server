package com.praise.push.common.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public record ResponseDto<T>(T data) {
    /**
     * returns http ok(200) response with body data
     */
    public static <T> ResponseEntity<T> ok(T data) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(data);
    }

    /**
     * returns http created(201) response with body data
     */
    public static <T> ResponseEntity<T> created(T data) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(data);
    }

    /**
     * returns http no content(204) response with empty body data
     */
    public static ResponseEntity<Void> noContent() {
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
