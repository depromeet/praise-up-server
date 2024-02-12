package com.praise.push.application.service;

import com.praise.push.adapter.out.persistence.UserRepository;
import com.praise.push.adapter.out.persistence.WithdrawalReasonRepository;
import com.praise.push.application.port.in.dto.UserPostStateResponseDto;
import com.praise.push.application.port.out.*;
import com.praise.push.domain.User;
import com.praise.push.domain.WithdrawalReason;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Random;
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
    private final RecordPostPort recordPostPort;
    private final RecordCommentPort recordCommentPort;

    String[] nicknames = {
        "새벽별빛", "파도소리", "별무리", "라이언", "어피치", "단무지", "프로도", "네오", "무지", "콘",
        "튜브", "제이지", "브라운", "코니", "샐리"
    };

    public LoginResponse doSocialLogin(KakaoAccount kakaoAccount) {
        Profile profile = kakaoAccount.getProfile();
        String email = kakaoAccount.getEmail();
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            Long userId = user.get().getId();
            return new LoginResponse(userId, false);
        } else {
            User newUser = User.builder()
                .email(email)
                .nickname(makeRandomNickname())
                .profileImage(profile.getProfile_image_url())
                .isSigned(true)
                .build();
            User savedUser = userRepository.save(newUser);
            return new LoginResponse(savedUser.getId(), true);
        }
    }

    private String makeRandomNickname() {
        Random random = new Random();
        return nicknames[random.nextInt(nicknames.length)];
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

        // 탈퇴하려는 유저가 작성한 게시글에 달린 반응을 모두 삭제
        recordCommentPort.deleteCommentsByUserId(user.getId());
        // 탈퇴하려는 유저가 작성한 게시글을 모두 삭제
        recordPostPort.deleteByUserId(user.getId());

        withdrawalReasonRepository.save(withdrawalReason);
        userRepository.deleteById(id);
    }

    public UserPostStateResponseDto getUserPostStatus(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User is not found"));

        UserPostStateResponseDto userPostStatusResponseDto;

        Date lastPostDate = user.getLastPostDate();
        if (lastPostDate == null || compareDate(lastPostDate)) {
            userPostStatusResponseDto = new UserPostStateResponseDto(true);
        } else {
            userPostStatusResponseDto = new UserPostStateResponseDto(false);
        }
        return userPostStatusResponseDto;
    }

    private boolean compareDate(Date lastPostDate) {
        Date today = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");   // yyyy-MM-dd HH:mm:ss

        // 이미 작성된 글이 있는 경우
        if (formatter.format(today).equals(formatter.format(lastPostDate))) {
            return false;
        }
        // 작성된 글이 없는 경우
        return true;
    }
}
