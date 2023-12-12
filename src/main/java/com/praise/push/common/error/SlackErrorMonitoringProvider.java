package com.praise.push.common.error;

import jakarta.servlet.http.HttpServletRequest;
import net.gpedro.integrations.slack.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class SlackErrorMonitoringProvider implements ErrorMonitoringProvider {

    @Value("${error.webhook.url}")
    private String webHookUrl;

    @Override
    public void push(final Exception exception, final HttpServletRequest request) {
        SlackApi slackApi = new SlackApi(webHookUrl);
        slackApi.call(createSlackMessage(exception, request));
    }

    /**
     * @return Slack error message with exception and request
     */
    private SlackMessage createSlackMessage(final Exception exception, final HttpServletRequest request) {
        return new SlackMessage()
                .setUsername("Praise-Push Error Monitoring Bot")
                .setText("5xx Error is Detected")
                .setAttachments(createSlackAttachments(exception, request))
                .setIcon(":rotating_light:");
    }

    /**
     * @return Slack attachments required to create the Slack message
     */
    private List<SlackAttachment> createSlackAttachments(final Exception exception, final HttpServletRequest request) {
        SlackAttachment slackAttachment = new SlackAttachment()
                .setFallback("Error")
                .setColor("danger")
                .setTitleLink(request.getContextPath())
                .setText(Arrays.toString(exception.getStackTrace()))
                .setFields(createSlackFields(request));

        return List.of(slackAttachment);
    }

    /**
     * @return Slack Fields to Create Slack Attachment Details
     */
    private List<SlackField> createSlackFields(final HttpServletRequest request) {
        return List.of(
                createSlackField("Request URL", request.getRequestURI()),
                createSlackField("Request Method", request.getMethod()),
                createSlackField("Request Time", LocalDateTime.now().toString()),
                createSlackField("Request IP", request.getRemoteAddr()),
                createSlackField("Request User-Agent", request.getHeader("User-Agent"))
        );
    }

    /**
     * @return Slack Field made with Title and Value.
     */
    private SlackField createSlackField(final String title, final String value) {
        return new SlackField().setTitle(title).setValue(value);
    }
}
