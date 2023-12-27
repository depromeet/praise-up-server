package com.praise.push.post.application.port.out;

import com.praise.push.post.domain.Keyword;

import java.util.List;

public interface LoadKeywordPort {
    /**
     * returns all keywords
     */
    List<Keyword> loadKeywords();
}
