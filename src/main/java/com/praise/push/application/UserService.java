package com.praise.push.application;

import com.praise.push.adapter.out.persistence.UserRepository;
import com.praise.push.application.port.out.KakaoAccount;
import com.praise.push.application.port.out.LoginResponse;
import com.praise.push.application.port.out.Profile;
import com.praise.push.application.port.out.UserResponse;
import com.praise.push.user.domain.User;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public LoginResponse doSocialLogin(KakaoAccount kakaoAccount) {
        Profile profile = kakaoAccount.getProfile();
        Optional<User> user = userRepository.findByNickname(profile.getNickname());
        if (user.isPresent()) {
            return new LoginResponse(user.get());
        } else {
            User newUser = User.builder()
                .nickname(profile.getNickname())
                .profileImage(profile.getProfile_image_url())
                .build();
            User savedUser = userRepository.save(newUser);
            return new LoginResponse(savedUser);
        }
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User is Not Found."));
        return new UserResponse(user);
    }
}
