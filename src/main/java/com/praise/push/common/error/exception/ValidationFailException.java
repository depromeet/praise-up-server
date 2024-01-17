package com.praise.push.common.error.exception;

import com.praise.push.common.error.model.ErrorCode;
import lombok.Getter;

@Getter
public class ValidationFailException extends RuntimeException {
    private final ErrorCode errorCode;

    public ValidationFailException(String violationMessage) {
        super(violationMessage);
        this.errorCode = ErrorCode.VALIDATION_CHECK_FAIL;
    }

    public ValidationFailException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
