package com.praise.push.batch.cron.post.scheduler;

import com.praise.push.batch.cron.post.jobs.PostBatchJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PostBatchScheduler {
    private final PostBatchJob postBatchJob;

    //@Scheduled(cron = "0 0 0 * * * ")
    @Scheduled(cron = "38 0 14 1 * *")
    public void runPostBatchJob() {
        log.info("batch execute!!");
        postBatchJob.run();
    }
}
