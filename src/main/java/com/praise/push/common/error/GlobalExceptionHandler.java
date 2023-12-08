package com.praise.push.common.error;

import jakarta.servlet.http.HttpServletRequest;
import net.gpedro.integrations.slack.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @Value("${slack.webhook-url:NoneWebHookUrl}")
    private String webHookUrl;

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(final RuntimeException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("RuntimeException Handler: " + exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(final Exception exception, final HttpServletRequest request) {
        pushSlackErrorMessage(exception, request);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Exception Handler: " + exception.getMessage());
    }

    /**
     * Push the error message with the slack using Exception and HttpServletRequest.
     */
    private void pushSlackErrorMessage(final Exception exception, final HttpServletRequest request) {
        try {
            SlackAttachment slackAttachment = new SlackAttachment();
            slackAttachment.setFallback("Error");
            slackAttachment.setColor("danger");
            slackAttachment.setTitle("5xx Error Detected");
            slackAttachment.setTitleLink(request.getContextPath());
            slackAttachment.setText(Arrays.toString(exception.getStackTrace()));
            slackAttachment.setFields(getSlackMessageFields(request));

            SlackMessage slackMessage = new SlackMessage();
            slackMessage.setAttachments(List.of(slackAttachment));
            slackMessage.setIcon(":rotating_light:");
            slackMessage.setText("5xx Error Detected");
            slackMessage.setUsername("Praise-Push Back End Developers");

            SlackApi slackApi = new SlackApi(webHookUrl);
            slackApi.call(slackMessage);
        } catch (SlackException ignored) {}
    }

    /**
     * @return Slack Error Message Fields List using HttpServletRequest.
     */
    private List<SlackField> getSlackMessageFields(HttpServletRequest request) {
        return List.of(
                new SlackField().setTitle("Request URL").setValue(request.getRequestURI()),
                new SlackField().setTitle("Request Method").setValue(request.getMethod()),
                new SlackField().setTitle("Request Time").setValue(LocalDateTime.now().toString()),
                new SlackField().setTitle("Request IP").setValue(request.getRemoteAddr()),
                new SlackField().setTitle("Request User-Agent").setValue(request.getHeader("User-Agent"))
        );
    }
}
