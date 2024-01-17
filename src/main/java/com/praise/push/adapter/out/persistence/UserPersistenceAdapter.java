package com.praise.push.adapter.out.persistence;

import com.praise.push.application.port.out.LoadUserPort;
import com.praise.push.common.error.exception.PraiseUpException;
import com.praise.push.common.error.model.ErrorCode;
import com.praise.push.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserPersistenceAdapter implements LoadUserPort {
    private final UserRepository userRepository;

    @Override
    public User loadUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new PraiseUpException(ErrorCode.NOT_FOUND));
    }
}
