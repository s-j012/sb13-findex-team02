package com.findex.team02.indexinfo.repository;

import com.findex.team02.indexinfo.entity.IndexInfo;
import com.findex.team02.indexinfo.entity.SourceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IndexInfoRepository extends JpaRepository<IndexInfo, Long> {

    List<IndexInfo> findByFavoriteTrue();

    List<IndexInfo> findBySourceType(SourceType sourceType);

    List<IndexInfo> findByIndexNameContaining(String keyword);
}