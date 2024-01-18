package com.praise.push.common.error.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    /*
     * 400 BAD_REQUEST: 잘못된 요청
     */
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "C-000", "Bad Request"),

    /*
     * 404 NOT_FOUND: 리소스를 찾을 수 없음
     */
    NOT_FOUND(HttpStatus.NOT_FOUND, "C-001", "Not Found the Contents"),

    /*
     * 405 METHOD_NOT_ALLOWED: 허용되지 않은 Request Method 호출
     */
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "C-002", "Method Not Allowed"),

    /*
     * 500 INTERNAL_SERVER_ERROR: 내부 서버 오류
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C-003", "Internal Server Error"),

    /*
     * 400 BAD_REQUEST: 요청 인자가 유효하지 않음
     */
    METHOD_ARGUMENT_NOT_VALID(HttpStatus.BAD_REQUEST, "C-004", "Method Argument Not Valid"),

    /**
     * 400 BAD_REQUEST: 최대 등록 가능 응답 개수 초과
     */
    EXCEED_MAXIMUM_COMMENTS_COUNT(HttpStatus.BAD_REQUEST, "C-005", "Exceed Maximum Comment Count"),

    /**
     * 400 BAD_REQUEST: 유효성 검사 실패
     */
    VALIDATION_CHECK_FAIL(HttpStatus.BAD_REQUEST, "C-006", "Fail Validation Check");

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;
}
