package com.praise.push.adapter.out.persistence;


import com.praise.push.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@AllArgsConstructor
@Table(name = "posts")
class PostJpaEntity extends BaseTimeEntity {

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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "keyword_id")
    private KeywordJpaEntity keyword;

    /**
     * 게시글 공개 여부
     */
    private Boolean visible;
}
