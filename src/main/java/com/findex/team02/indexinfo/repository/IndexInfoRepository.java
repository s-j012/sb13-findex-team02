package com.findex.team02.indexinfo.repository;

import com.findex.team02.indexinfo.entity.IndexInfo;
import com.findex.team02.indexinfo.entity.SourceType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//지수 정보 DB 접근 리포지토리
public interface IndexInfoRepository extends JpaRepository<IndexInfo, Long>,
    JpaSpecificationExecutor<IndexInfo> {
  // 지수 분류명과 지수명 중복 체크용
  boolean existsByIndexClassificationAndIndexName(String indexClassification, String indexName);

  List<IndexInfo> findByFavoriteTrue();

  List<IndexInfo> findBySourceType(SourceType sourceType);

  List<IndexInfo> findByIndexNameContaining(String keyword);
}