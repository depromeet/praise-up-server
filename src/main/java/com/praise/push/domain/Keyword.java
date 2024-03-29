package com.praise.push.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@AllArgsConstructor
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
     * 선택된 횟수
     */
    private int selectedCount;
}
