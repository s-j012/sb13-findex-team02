package com.findex.team02.indexdata.controller;

import com.findex.team02.indexinfo.dto.IndexChartDto;
import com.findex.team02.indexdata.service.IndexDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// 지수 데이터 관련 API를 처리하는 Controller
// 차트 조회, CSV export 등 index_data와 관련된 요청을 담당함
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/index-data")
public class IndexDataController {

    // 지수 데이터 비즈니스 로직을 처리하는 Service
    private final IndexDataService indexDataService;

    // 지수 차트 조회 API
    //
    // 요청 예시:
    // GET /api/index-data/17/chart?periodType=monthly
    //
    // PathVariable:
    // id = 지수 정보 ID
    //
    // QueryParam:
    // periodType = monthly / quarterly / yearly
    //
    // 응답:
    // - 지수 기본 정보
    // - 날짜별 종가 데이터
    // - 5일 이동평균 데이터
    // - 20일 이동평균 데이터
    @GetMapping("/{id}/chart")
    public ResponseEntity<IndexChartDto> getChart(
            @PathVariable Long id,
            @RequestParam(required = false, defaultValue = "monthly") String periodType
    ) {
        // Service에 지수 ID와 기간 유형을 넘겨 차트 데이터 생성
        IndexChartDto response = indexDataService.getChart(id, periodType);

        // HTTP 200 OK와 함께 응답 반환
        return ResponseEntity.ok(response);
    }
}