package com.praise.push.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface CommentRepository extends JpaRepository<CommentJpaEntity, Long> {
}
