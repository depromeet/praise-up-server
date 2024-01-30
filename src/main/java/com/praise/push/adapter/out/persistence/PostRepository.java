package com.praise.push.adapter.out.persistence;

import com.praise.push.domain.Post;
import com.praise.push.domain.model.PostWithCommentCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT new com.praise.push.domain.model.PostWithCommentCount(p, COUNT(c)) " +
            "FROM posts p " +
            "JOIN p.user u " +
            "LEFT JOIN comments c ON c.post.id = p.id " +
            "WHERE u.id = :userId AND p.isRead = true AND p.visible = true " +
            "GROUP BY p " +
            "ORDER BY p.id DESC "
    )
    Page<PostWithCommentCount> findReadAndVisiblePostsWithCommentCount(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT new com.praise.push.domain.model.PostWithCommentCount(p, COUNT(c)) " +
            "FROM posts p " +
            "JOIN p.user u " +
            "LEFT JOIN comments c ON c.post.id = p.id " +
            "WHERE u.id = :userId AND p.isRead = false " +
            "GROUP BY p " +
            "ORDER BY p.id ASC")
    List<PostWithCommentCount> findUnreadPostsWithCommentCount(@Param("userId") Long userId);

    @Modifying
    @Query("UPDATE posts p SET p.visible = true WHERE p.visible = false and p.createdDate < :dateTime")
    void updatePostsVisibleIsBeforeDateTime(@Param("dateTime") LocalDateTime dateTime);
}
