package com.praise.push.common;

/**
 * 상황에 맞게 변경하여 사용
 */
public class CustomException extends RuntimeException {
    private CustomErrorCode customErrorCode;
    private String message;

    public CustomException(CustomErrorCode customErrorCode) {
        super(customErrorCode.getStatusMessage());
        this.customErrorCode = customErrorCode;
        this.message = customErrorCode.getStatusMessage();
    }
}
