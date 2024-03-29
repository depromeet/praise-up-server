package com.praise.push.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users", indexes = {@Index(name = "idx_email", columnList = "email")})
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
    @Column(length = 4, nullable = true)
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
    @Column(length = 50)
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

    /**
     * 첫 회원 가입 여부
     */
    @Column
    private Boolean isSigned;

    /**
     * 최근 post 작성 일자
     */
    @Column
    private Date lastPostDate;

    @Builder
    public User(String nickname, String profileImage, LocalDate birthday, String email, String phoneNumber, Boolean isSigned) {
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.birthday = birthday;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isSigned = isSigned;
    }

    public User changeNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }
}
