package com.praise.push.post.adapter.out.persistence;

import com.praise.push.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@AllArgsConstructor
@Table(name = "keywords")
public class KeywordJpaEntity extends BaseTimeEntity {
    /**
     * 키워드 아이디
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keyword_id")
    private Long id;

    /**
     * 키워드
     */
    private String keyword;

    /**
     * 게시글
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "keyword", cascade = CascadeType.ALL)
    private List<PostJpaEntity> posts;
}
