package com.praise.push.application.port.out;

import com.praise.push.domain.User;

public interface LoadUserPort {
    /**
     * return user with matching user_id
     */
    User loadUserById(Long userId);
}
