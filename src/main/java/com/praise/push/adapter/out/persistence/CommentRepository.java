package com.praise.push.adapter.out.persistence;

import com.praise.push.domain.Comment;
import com.praise.push.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

interface CommentRepository extends JpaRepository<Comment, Long> {

    Page<Comment> findAllByPostOrderByIdDesc(Post post, Pageable pageable);

    @Modifying
    @Query(value = "delete from comments c where c.post.id = :postId")
    void deleteByPostId(@Param("postId") Long postId);

    @Query("select count(co.id) from comments co where co.post.id = :postId")
    Long getCountByPostId(@Param("postId") Long postId);
}
