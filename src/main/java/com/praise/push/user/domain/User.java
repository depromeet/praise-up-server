package com.praise.push.user.domain;

import com.praise.push.domain.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
public class User extends BaseTimeEntity {

    /**
     * 사용자 아이디
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    /**
     * 사용자 닉네임
     */
    @Column(length = 20, nullable = false)
    private String nickname;

    /**
     * 사용자 프로필 이미지
     */
    @Column(length = 250)
    private String profileImage;

    /**
     * 생년월일
     */
    private LocalDate birthday;

    /**
     * 이메일
     */
    @Column(length = 20)
    private String email;

    /**
     * 휴대폰 번호
     */
    @Column(length = 20)
    private String phoneNumber;

    /**
     * 가입 일자
     */
    @Column
    private LocalDateTime joinedDate;

    /**
     * 탈퇴 일자
     */
    @Column
    private LocalDateTime deletedDate;

    /**
     * 사용자 인증 정보
     */
    @OneToOne(mappedBy = "user")
    private UserAuth userAuth;

    @Builder
    public User(String nickname, String profileImage, LocalDate birthday, String email, String phoneNumber) {
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.birthday = birthday;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
