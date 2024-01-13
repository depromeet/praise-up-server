package com.praise.push.batch.cron.post.jobs;

import com.praise.push.application.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostBatchJob {
    private final PostService postService;
    public void run() {
        postService.updateOpenStatus();
    }
}
