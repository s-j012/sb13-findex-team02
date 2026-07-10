package com.findex.team02.indexdata.service;

import com.findex.team02.indexdata.dto.ChartDataPointDto;
import com.findex.team02.indexinfo.dto.IndexChartDto;
import com.findex.team02.indexdata.entity.IndexData;
import com.findex.team02.indexdata.repository.IndexDataRepository;
import com.findex.team02.indexinfo.entity.IndexInfo;
import com.findex.team02.indexinfo.repository.IndexInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 지수 데이터 관련 비즈니스 로직을 처리하는 Service
// 차트 조회, 이동평균 계산, CSV export 등에 사용됨
@Service
@RequiredArgsConstructor
public class IndexDataService {

    // 지수 데이터 Repository
    // index_data 테이블 조회에 사용
    private final IndexDataRepository indexDataRepository;

    // 지수 정보 Repository
    // index_infos 테이블 조회에 사용
    private final IndexInfoRepository indexInfoRepository;

    // 지수 차트 조회
    // indexInfoId와 periodType을 받아 차트 응답 DTO를 생성함
    public IndexChartDto getChart(Long indexInfoId, String periodTypeValue) {
        // 요청으로 들어온 periodType 문자열을 enum으로 변환
        // null 또는 빈 값이면 MONTHLY 기본값 사용
        IndexChartDto.ChartPeriodType periodType = IndexChartDto.ChartPeriodType.from(periodTypeValue);

        // 지수 정보 조회
        // 존재하지 않는 id면 예외 발생
        IndexInfo indexInfo = indexInfoRepository.findById(indexInfoId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 지수 정보입니다. id=" + indexInfoId));

        // periodType에 따라 조회 시작일 계산
        // MONTHLY: 최근 1개월
        // QUARTERLY: 최근 3개월
        // YEARLY: 최근 1년
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = calculateStartDate(endDate, periodType);

        // 해당 지수의 기간 내 데이터를 날짜 오름차순으로 조회
        // 차트는 보통 오래된 날짜부터 최신 날짜 순서로 그리는 것이 자연스러움
        List<IndexData> indexDataList =
                indexDataRepository.findByIndexInfoIdAndBaseDateBetweenOrderByBaseDateAsc(
                        indexInfoId,
                        startDate,
                        endDate
                );

        // 날짜별 종가 데이터 생성
        List<ChartDataPointDto> dataPoints = toDataPoints(indexDataList);

        // 5일 이동평균 데이터 생성
        List<ChartDataPointDto> ma5DataPoints = calculateMovingAverage(indexDataList, 5);

        // 20일 이동평균 데이터 생성
        List<ChartDataPointDto> ma20DataPoints = calculateMovingAverage(indexDataList, 20);

        // Swagger에서 확인한 응답 구조에 맞춰 DTO 반환
        return new IndexChartDto(
                indexInfo.getId(),
                indexInfo.getIndexClassification(),
                indexInfo.getIndexName(),
                periodType,
                dataPoints,
                ma5DataPoints,
                ma20DataPoints
        );
    }

    // periodType에 따라 조회 시작일 계산
    private LocalDate calculateStartDate(LocalDate endDate, IndexChartDto.ChartPeriodType periodType) {
        return switch (periodType) {
            case MONTHLY -> endDate.minusMonths(1);
            case QUARTERLY -> endDate.minusMonths(3);
            case YEARLY -> endDate.minusYears(1);
        };
    }

    // IndexData 목록을 차트 데이터 포인트 목록으로 변환
    // 기준일자와 종가를 사용함
    private List<ChartDataPointDto> toDataPoints(List<IndexData> indexDataList) {
        List<ChartDataPointDto> dataPoints = new ArrayList<>();

        for (IndexData indexData : indexDataList) {
            dataPoints.add(new ChartDataPointDto(
                    indexData.getBaseDate(),
                    indexData.getClosingPrice()
            ));
        }

        return dataPoints;
    }

    // 이동평균 계산
    // windowSize가 5면 5일 이동평균, 20이면 20일 이동평균
    private List<ChartDataPointDto> calculateMovingAverage(List<IndexData> indexDataList, int windowSize) {
        // 데이터 개수가 이동평균 계산에 필요한 개수보다 적으면 빈 리스트 반환
        if (indexDataList.size() < windowSize) {
            return Collections.emptyList();
        }

        List<ChartDataPointDto> movingAveragePoints = new ArrayList<>();

        // windowSize - 1 인덱스부터 이동평균 계산 가능
        // 예: 5일 이동평균은 5번째 데이터부터 계산 가능
        for (int i = windowSize - 1; i < indexDataList.size(); i++) {
            BigDecimal sum = BigDecimal.ZERO;

            // 현재 위치 기준으로 windowSize개만큼 종가 합산
            for (int j = i - windowSize + 1; j <= i; j++) {
                sum = sum.add(indexDataList.get(j).getClosingPrice());
            }

            // 평균 계산
            BigDecimal average = sum.divide(
                    BigDecimal.valueOf(windowSize),
                    2,
                    RoundingMode.HALF_UP
            );

            // 이동평균 날짜는 현재 위치의 날짜를 사용
            movingAveragePoints.add(new ChartDataPointDto(
                    indexDataList.get(i).getBaseDate(),
                    average
            ));
        }

        return movingAveragePoints;
    }
}