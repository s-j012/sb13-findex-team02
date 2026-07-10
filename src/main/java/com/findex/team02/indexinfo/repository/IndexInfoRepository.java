package com.findex.team02.indexinfo.repository;

import com.findex.team02.indexinfo.entity.IndexInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// index_infos 테이블에 접근하기 위한 Repository
// 지수 정보 조회, 관심 지수 조회 등에 사용됨
public interface IndexInfoRepository extends JpaRepository<IndexInfo, Long> {

    // favorite 값이 true인 지수 정보만 조회
    // 관심 지수 성과 조회에서 사용됨
    List<IndexInfo> findByFavoriteTrue();

    // sourceType 기준으로 지수 정보 조회
    // 예: OPEN_API, USER
    // 나중에 출처별 필터가 필요할 때 사용할 수 있음
    List<IndexInfo> findBySourceType(String sourceType);

    // 지수명에 특정 키워드가 포함된 지수 정보 조회
    // 검색 기능이 필요할 때 사용할 수 있음
    List<IndexInfo> findByIndexNameContaining(String keyword);
}