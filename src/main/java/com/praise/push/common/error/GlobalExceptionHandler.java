package com.praise.push.common.error;

import com.praise.push.common.error.exception.PraiseUpException;
import com.praise.push.common.error.exception.ValidationFailException;
import com.praise.push.common.error.model.ErrorCode;
import com.praise.push.common.error.model.ErrorResponseDto;
import com.praise.push.common.monitoring.MonitoringProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final MonitoringProvider monitoringProvider;

    /**
     *
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidException(
            final MethodArgumentNotValidException exception
    ) {
        String message = exception.getFieldErrors().getFirst().getDefaultMessage();

        return ErrorResponseDto.build(ErrorCode.VALIDATION_CHECK_FAIL, message);
    }

    /**
     * Handle exception that occur due to incorrect type of request parameter
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentTypeMismatchException(
            final MethodArgumentTypeMismatchException exception
    ) {
        String parameterName = exception.getParameter().getParameterName();
        String parameterType = exception.getParameter().getParameterType().getSimpleName();
        String message = String.format("%s is must be %s.", parameterName, parameterType);

        return ErrorResponseDto.build(ErrorCode.VALIDATION_CHECK_FAIL, message);
    }

    /**
     * Handle exception that occurs when request parameter is null
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponseDto> handleMissingServletRequestParameterException(
            final MissingServletRequestParameterException exception
    ) {
        String parameterName = exception.getParameterName();
        String message = String.format("%s is must not be null.", parameterName);

        return ErrorResponseDto.build(ErrorCode.VALIDATION_CHECK_FAIL, message);
    }

    /**
     * Handle exception that occurs when validation check fail
     */
    @ExceptionHandler(ValidationFailException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationFailException(
            final ValidationFailException exception
    ) {
        return ErrorResponseDto.build(exception.getErrorCode(), exception.getMessage());
    }

    @ExceptionHandler(PraiseUpException.class)
    public ResponseEntity<ErrorResponseDto> handlePraiseUpException(
            final PraiseUpException exception,
            final HttpServletRequest request
    ) {
        log.error("Exception: {}, Request URI: {}", exception.getMessage(), request.getRequestURL());

        return ErrorResponseDto.build(exception.getErrorCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(
            final Exception exception,
            final HttpServletRequest request
    ) {
        log.error("Exception: {}, Request URI: {}", exception.getMessage(), request.getRequestURL());
        monitoringProvider.push(exception, request);

        return ErrorResponseDto.build(ErrorCode.INTERNAL_SERVER_ERROR);
    }
}
