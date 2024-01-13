package com.praise.push.adapter.out.persistence;

import com.praise.push.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

interface PostRepository extends JpaRepository<Post, Long> {
}
