package com.praise.push.application.service;

import com.praise.push.adapter.out.persistence.UserRepository;
import com.praise.push.adapter.out.persistence.WithdrawalReasonRepository;
import com.praise.push.application.port.out.KakaoAccount;
import com.praise.push.application.port.out.LoginResponse;
import com.praise.push.application.port.out.Profile;
import com.praise.push.application.port.out.UserResponse;
import com.praise.push.domain.User;
import com.praise.push.domain.WithdrawalReason;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final WithdrawalReasonRepository withdrawalReasonRepository;

    public LoginResponse doSocialLogin(KakaoAccount kakaoAccount) {
        Profile profile = kakaoAccount.getProfile();
        String email = kakaoAccount.getEmail();
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            return new LoginResponse(user.get());
        } else {
            User newUser = User.builder()
                .email(email)
                .nickname(profile.getNickname())
                .profileImage(profile.getProfile_image_url())
                .build();
            User savedUser = userRepository.save(newUser);
            return new LoginResponse(savedUser);
        }
    }

    @Transactional(readOnly = true)
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User is not Found."));
        return new UserResponse(user);
    }

    @Transactional
    public UserResponse changeNickname(Long id, String nickname) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("User is not found"));

        User changeUser = user.changeNickname(nickname);
        return new UserResponse(changeUser);
    }

    @Transactional
    public void deleteUser(Long id, String reason) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("User is not found"));

        WithdrawalReason withdrawalReason = WithdrawalReason.builder()
            .userId(user.getId())
            .reason(reason)
            .build();

        withdrawalReasonRepository.save(withdrawalReason);
        userRepository.deleteById(id);
    }
}
