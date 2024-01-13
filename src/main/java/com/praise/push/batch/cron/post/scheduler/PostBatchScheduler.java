package com.praise.push.batch.cron.post.scheduler;

import com.praise.push.batch.cron.post.jobs.PostBatchJob;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostBatchScheduler {
    private final PostBatchJob postBatchJob;

    @Scheduled(cron = "0 0 * * * ")
    public void runPostBatchJob() {
        postBatchJob.run();
    }

}
