package com.findex.team02.indexdata.repository;

import com.findex.team02.indexdata.entity.IndexData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

// index_data 테이블에 접근하기 위한 Repository
// 지수 데이터 목록 조회, 차트 조회, CSV export, 성과 계산 등에 사용됨
public interface IndexDataRepository extends JpaRepository<IndexData, Long> {

    // 특정 지수 정보 ID에 해당하는 지수 데이터를 기준일자 내림차순으로 조회
    // 최신 데이터부터 가져올 때 사용
    List<IndexData> findByIndexInfoIdOrderByBaseDateDesc(Long indexInfoId);

    // 특정 지수 정보 ID에 해당하는 지수 데이터를 기준일자 오름차순으로 조회
    // 차트에서 오래된 날짜부터 보여줘야 할 때 사용
    List<IndexData> findByIndexInfoIdOrderByBaseDateAsc(Long indexInfoId);

    // 특정 지수 정보 ID와 날짜 범위에 해당하는 지수 데이터를 기준일자 내림차순으로 조회
    // CSV export에서 desc 정렬일 때 사용 가능
    List<IndexData> findByIndexInfoIdAndBaseDateBetweenOrderByBaseDateDesc(
            Long indexInfoId,
            LocalDate startDate,
            LocalDate endDate
    );

    // 특정 지수 정보 ID와 날짜 범위에 해당하는 지수 데이터를 기준일자 오름차순으로 조회
    // 차트 조회나 CSV export에서 asc 정렬일 때 사용 가능
    List<IndexData> findByIndexInfoIdAndBaseDateBetweenOrderByBaseDateAsc(
            Long indexInfoId,
            LocalDate startDate,
            LocalDate endDate
    );

    // 특정 지수의 가장 최신 지수 데이터 1건 조회
    // 성과 계산에서 currentPrice를 구할 때 사용
    Optional<IndexData> findTopByIndexInfoIdOrderByBaseDateDesc(Long indexInfoId);

    // 특정 지수의 가장 오래된 지수 데이터 1건 조회
    // 전체 데이터 기준 beforePrice가 필요할 때 사용할 수 있음
    Optional<IndexData> findTopByIndexInfoIdOrderByBaseDateAsc(Long indexInfoId);

    // 특정 지수에서 기준일자가 startDate 이상인 데이터 중 가장 오래된 데이터 1건 조회
    // 기간 성과 계산에서 beforePrice를 구할 때 사용
    Optional<IndexData> findTopByIndexInfoIdAndBaseDateGreaterThanEqualOrderByBaseDateAsc(
            Long indexInfoId,
            LocalDate startDate
    );

    // 특정 지수에서 기준일자가 endDate 이하인 데이터 중 가장 최신 데이터 1건 조회
    // 기간 성과 계산에서 currentPrice를 구할 때 사용
    Optional<IndexData> findTopByIndexInfoIdAndBaseDateLessThanEqualOrderByBaseDateDesc(
            Long indexInfoId,
            LocalDate endDate
    );
}