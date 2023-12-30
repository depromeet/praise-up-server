package com.praise.push.application.port.out;

import com.praise.push.domain.Keyword;

import java.util.List;

public interface LoadKeywordPort {
    /**
     * returns all keywords
     */
    List<Keyword> loadKeywords();
}
