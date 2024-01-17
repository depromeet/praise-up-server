package com.praise.push.adapter.out.persistence;

import com.praise.push.domain.Post;
import com.praise.push.domain.model.PostWithCommentCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT new com.praise.push.domain.model.PostWithCommentCount(p, COUNT(c)) " +
            "FROM posts p LEFT JOIN comments c ON p.id = c.post.id " +
            "WHERE  p.visible = true " +
            "GROUP BY p.id " +
            "ORDER BY p.id DESC ")
    Page<PostWithCommentCount> findAllPostsWithCommentCount(Pageable pageable);
}
