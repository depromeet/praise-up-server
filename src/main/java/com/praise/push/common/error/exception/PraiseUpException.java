package com.praise.push.common.error.exception;

import com.praise.push.common.error.enums.PraiseUpErrorCode;

public class PraiseUpException extends RuntimeException {
    private PraiseUpErrorCode praiseUpErrorCode;
    private String message;

    public PraiseUpException(PraiseUpErrorCode praiseUpErrorCode) {
        super(praiseUpErrorCode.getStatusMessage());
        this.praiseUpErrorCode = praiseUpErrorCode;
        this.message = praiseUpErrorCode.getStatusMessage();
    }
}
