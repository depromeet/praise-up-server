package com.praise.push.batch.cron.post.scheduler;

import com.praise.push.batch.cron.post.jobs.KeywordBatchJob;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KeywordBatchScheduler {
    private final KeywordBatchJob keywordBatchJob;

    @Scheduled(cron = "0 0 0 * * *")
    public void runUpdateSelectedCountJob() {
        keywordBatchJob.runUpdateSelectedCount();
    }
}
