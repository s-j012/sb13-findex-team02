package com.findex.team02.indexdata.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

// 차트에 표시할 데이터 포인트 DTO
// 날짜와 해당 날짜의 값으로 구성됨
public class ChartDataPointDto {

    // 차트 X축에 표시될 날짜
    // 예: 2026-07-07
    private LocalDate date;

    // 차트 Y축에 표시될 값
    // 일반 차트에서는 종가, 이동평균선에서는 평균값이 들어감
    private BigDecimal value;

    // 기본 생성자
    // JSON 직렬화/역직렬화나 프레임워크 사용 시 필요할 수 있음
    public ChartDataPointDto() {
    }

    // 날짜와 값을 받아 차트 데이터 포인트를 생성하는 생성자
    public ChartDataPointDto(LocalDate date, BigDecimal value) {
        this.date = date;
        this.value = value;
    }

    // 날짜 반환
    public LocalDate getDate() {
        return date;
    }

    // 값 반환
    public BigDecimal getValue() {
        return value;
    }
}