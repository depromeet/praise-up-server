package com.praise.push.post.domain;

import com.praise.push.comment.domain.Comment;
import com.praise.push.domain.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "keywords")
public class Keyword extends BaseTimeEntity {

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
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "keyword")
    private List<Post> posts;

    /**
     * 댓글
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    private List<Comment> comments;

}
