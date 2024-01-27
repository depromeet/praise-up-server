package com.praise.push.batch.cron.post.jobs;

import com.praise.push.application.service.KeywordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KeywordBatchJob {
    private static final int UPDATE_SIZE = 5;

    private final KeywordService keywordService;

    public void runUpdateSelectedCount() {
        log.info("Execute Keyword Update Selected Count Job");
        keywordService.updateSelectedCount(UPDATE_SIZE);
    }
}
