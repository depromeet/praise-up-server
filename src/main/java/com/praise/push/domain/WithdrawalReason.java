package com.praise.push.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "withdrawal_reason")
public class WithdrawalReason {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "withdrawal_reason_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "reason", length = 500)
    private String reason;

    @Builder
    public WithdrawalReason(Long userId, String reason) {
        this.userId = userId;
        this.reason = reason;
    }
}
