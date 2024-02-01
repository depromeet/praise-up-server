package com.praise.push.common.monitoring;

import com.praise.push.common.error.model.ErrorEvent;
import com.praise.push.common.error.model.ErrorRequest;
import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackAttachment;
import net.gpedro.integrations.slack.SlackField;
import net.gpedro.integrations.slack.SlackMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class SlackMonitoringProvider implements MonitoringProvider {

    @Value("${error.webhook.url}")
    private String webHookUrl;

    @Async
    @Override
    public void pushError(final ErrorEvent errorEvent) {
        SlackApi slackApi = new SlackApi(webHookUrl);
        slackApi.call(createSlackMessage(errorEvent.exception(), errorEvent.request()));
    }

    /**
     * @return Slack error message with exception and request
     */
    private SlackMessage createSlackMessage(final Exception exception, final ErrorRequest request) {
        return new SlackMessage()
                .setUsername("Praise-Push Error Monitoring Bot")
                .setText("🚨 5xx Error is Detected!")
                .setAttachments(createSlackAttachments(exception, request))
                .setIcon(":rotating_light:");
    }

    /**
     * @return Slack attachments required to create the Slack message
     */
    private List<SlackAttachment> createSlackAttachments(final Exception exception, final ErrorRequest request) {
        SlackAttachment slackAttachment = new SlackAttachment()
                .setFallback("Error")
                .setColor("danger")
                .setFields(
                        List.of(
                                createSlackField("Error Message", exception.getMessage()),
                                createSlackField("Request URL", request.requestURI()),
                                createSlackField("Request Method", request.method()),
                                createSlackField("Request Time", LocalDateTime.now().toString()),
                                createSlackField("Request IP", request.remoteAddress()),
                                createSlackField("Request User-Agent", request.userAgent())
                        )
                );

        return List.of(slackAttachment);
    }

    /**
     * @return Slack Field made with Title and Value.
     */
    private SlackField createSlackField(final String title, final String value) {
        return new SlackField().setTitle(title).setValue(value);
    }
}
