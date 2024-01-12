package com.praise.push.adapter.out.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

interface CommentRepository extends JpaRepository<CommentJpaEntity, Long> {

    Page<CommentJpaEntity> findAllByPostOrderByIdDesc(PostJpaEntity post, Pageable pageable);

    @Modifying
    @Query(value = "delete from comments c where c.post.id = :postId")
    void deleteByPostId(@Param("postId") Long postId);
}
