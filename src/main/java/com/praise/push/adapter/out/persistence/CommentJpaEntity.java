package com.praise.push.adapter.out.persistence;

import com.praise.push.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "comments")
class CommentJpaEntity extends BaseTimeEntity {

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
    @ManyToOne(optional = false)
    @JoinColumn(name = "post_id")
    private PostJpaEntity post;

    @Builder
    public CommentJpaEntity(Long id, String nickname, String content, String imageUrl, PostJpaEntity post) {
        this.id = id;
        this.nickname = nickname;
        this.content = content;
        this.imageUrl = imageUrl;
        this.post = post;
    }
}

