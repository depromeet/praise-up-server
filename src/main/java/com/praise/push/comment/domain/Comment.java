package com.praise.push.comment.domain;

import com.praise.push.domain.BaseTimeEntity;
import com.praise.push.post.domain.Post;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "comments")
public class Comment extends BaseTimeEntity {

    /**
     * 댓글 아이디
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    /**
     * 내용
     */
    private String content;

    /**
     * 이미지
     */
    private String imageUrl;

    /**
     * 게시글
     */
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

}
