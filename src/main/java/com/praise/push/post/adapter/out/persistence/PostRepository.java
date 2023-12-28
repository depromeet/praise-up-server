package com.praise.push.post.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface PostRepository extends JpaRepository<PostJpaEntity, Long> {
}
