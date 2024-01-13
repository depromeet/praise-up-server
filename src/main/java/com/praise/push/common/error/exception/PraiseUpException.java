package com.praise.push.common.error.exception;

import com.praise.push.common.error.model.ErrorCode;
import lombok.Getter;

@Getter
public class PraiseUpException extends RuntimeException {
    private final ErrorCode errorCode;

    public PraiseUpException(ErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
    }

    public PraiseUpException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

}
