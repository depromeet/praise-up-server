package com.praise.push.adapter.out.persistence;

import com.praise.push.domain.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

interface KeywordRepository extends JpaRepository<Keyword, Long> {
}
