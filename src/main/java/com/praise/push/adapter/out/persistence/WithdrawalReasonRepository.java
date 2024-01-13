package com.praise.push.adapter.out.persistence;

import com.praise.push.domain.WithdrawalReason;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawalReasonRepository extends JpaRepository<WithdrawalReason, Long> {
}
