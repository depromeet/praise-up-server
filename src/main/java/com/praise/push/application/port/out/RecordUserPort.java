package com.praise.push.application.port.out;

import java.util.Date;

public interface RecordUserPort {
    void updateUserPostCreatedState(Long userId, Date postCreatedDate);
}
