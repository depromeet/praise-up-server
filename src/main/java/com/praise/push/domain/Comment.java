package com.praise.push.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "comments")
public class Comment extends BaseTimeEntity {

    /**
     * comment's id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    /**
     * nickname entered by comment author
     */
    private String nickname;

    /**
     * content entered by comment author
     */
    private String content;

    /**
     * image uploaded by comment author
     */
    private String imageUrl;

    /**
     * related post
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public Comment(Long id, String nickname, String content, String imageUrl, Post post) {
        this.id = id;
        this.nickname = nickname;
        this.content = content;
        this.imageUrl = imageUrl;
        this.post = post;
    }
}

