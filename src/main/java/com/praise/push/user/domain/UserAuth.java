package com.praise.push.user.domain;

import com.praise.push.user.domain.vo.OAuthProvider;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user_auth")
public class UserAuth {

    /**
     * 사용자 인증 아이디
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_auth_Id")
    private Long id;

    /**
     * OAuth 제공자 아이디
     */
    private Long oauthId;

    /**
     * OAuth 제공자
     */
    @Enumerated(EnumType.STRING)
    private OAuthProvider oAuthProvider;

    /**
     * 사용자
     */
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
