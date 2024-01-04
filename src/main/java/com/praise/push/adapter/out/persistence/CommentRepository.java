package com.praise.push.adapter.out.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

interface CommentRepository extends JpaRepository<CommentJpaEntity, Long> {

    Page<CommentJpaEntity> findAllByPostOrderByIdDesc(PostJpaEntity post, Pageable pageable);
}
