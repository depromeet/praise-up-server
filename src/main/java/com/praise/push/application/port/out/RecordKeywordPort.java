package com.praise.push.application.port.out;

import java.util.List;

public interface RecordKeywordPort {
    /**
     * increase selected count of Keywords in ids by 1
     */
    void updateSelectedCount(List<Long> ids);
}
