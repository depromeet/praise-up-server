package com.praise.push.application.port.out;

import com.praise.push.domain.Keyword;

import java.util.List;
import java.util.Optional;

public interface LoadKeywordPort {
    /**
     * returns all keywords
     */
    List<Keyword> loadKeywords();

    /**
     * returns keyword with matching id
     */
    Keyword loadKeywordById(Long keywordId);
}
