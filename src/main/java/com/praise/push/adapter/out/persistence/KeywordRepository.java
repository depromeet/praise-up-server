package com.praise.push.adapter.out.persistence;

import com.praise.push.domain.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

interface KeywordRepository extends JpaRepository<Keyword, Long> {

    @Modifying
    @Query("update Keyword k set k.selectedCount = k.selectedCount + 1 where k.id in (:ids)")
    void updateSelectedCount(@Param("ids") List<Long> ids);
}
