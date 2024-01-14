package com.praise.push.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "posts")
@Builder
@AllArgsConstructor
public class Post extends BaseTimeEntity {

    /**
     * 게시글 아이디
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    /**
     * 게시글 본문
     */
    private String content;

    /**
     * 게시글 이미지
     */
    private String imageUrl;

    /**
     * 키워드
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "keyword_id")
    private Keyword keyword;

    /**
     * 게시글 공개 여부
     */
    private Boolean visible;

    /**
     * 게시글 읽음 여부
     */
    private Boolean isRead;

    public Post changeOpen(boolean status) {
        this.visible = status;
        return this;
    }
}
