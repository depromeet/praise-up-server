package com.praise.push.post.domain;

import com.praise.push.domain.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "posts")
public class Post extends BaseTimeEntity {

    /**
     * 게시글 아이디
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    /**
     * 게시글 제목
     */
    private String title;

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
    @ManyToOne
    @JoinColumn(name = "keyword_id")
    private Keyword keyword;

}
