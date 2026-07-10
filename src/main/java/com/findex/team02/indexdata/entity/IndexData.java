package com.findex.team02.indexdata.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

// index_data 테이블과 매핑되는 JPA Entity
// 지수의 날짜별 시가, 종가, 고가, 저가, 거래량 등의 데이터를 저장하는 객체
@Entity
@Table(name = "index_data")
public class IndexData {

    // 지수 데이터 고유 ID
    // DB의 id 컬럼과 매핑
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 지수 정보 ID
    // index_infos 테이블의 id를 참조하는 값
    // 예: KRX 300 정보기술 지수의 ID가 17이면 indexInfoId = 17
    @Column(name = "index_info_id", nullable = false)
    private Long indexInfoId;

    // 기준 날짜
    // 해당 지수 데이터가 어느 날짜의 데이터인지 나타냄
    @Column(name = "base_date", nullable = false)
    private LocalDate baseDate;

    // 데이터 출처
    // 예: OPEN_API, USER
    @Column(name = "source_type", nullable = false)
    private String sourceType;

    // 시가
    // 장 시작 시점의 지수 가격
    @Column(name = "market_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal marketPrice;

    // 종가
    // 장 종료 시점의 지수 가격
    // 차트 조회, 성과 계산에서 가장 많이 사용됨
    @Column(name = "closing_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal closingPrice;

    // 고가
    // 해당 날짜의 가장 높은 지수 가격
    @Column(name = "high_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal highPrice;

    // 저가
    // 해당 날짜의 가장 낮은 지수 가격
    @Column(name = "low_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal lowPrice;

    // 전일 대비 값
    // 현재 종가와 이전 기준 가격의 차이
    @Column(name = "versus", nullable = false, precision = 10, scale = 2)
    private BigDecimal versus;

    // 등락률
    // 전일 대비 몇 퍼센트 상승/하락했는지 나타냄
    @Column(name = "fluctuation_rate", nullable = false, precision = 10, scale = 2)
    private BigDecimal fluctuationRate;

    // 거래량
    // 해당 날짜에 거래된 수량
    @Column(name = "trading_quantity", nullable = false)
    private Long tradingQuantity;

    // 거래대금
    // 해당 날짜의 총 거래 금액
    @Column(name = "trading_price", nullable = false)
    private Long tradingPrice;

    // 시가총액
    // 해당 날짜 기준 시가총액
    @Column(name = "market_total_amount", nullable = false)
    private Long marketTotalAmount;

    // JPA 기본 생성자
    // JPA가 Entity 객체를 생성할 때 필요함
    protected IndexData() {
    }

    // 테스트 데이터 생성이나 직접 객체 생성 시 사용할 생성자
    public IndexData(
            Long indexInfoId,
            LocalDate baseDate,
            String sourceType,
            BigDecimal marketPrice,
            BigDecimal closingPrice,
            BigDecimal highPrice,
            BigDecimal lowPrice,
            BigDecimal versus,
            BigDecimal fluctuationRate,
            Long tradingQuantity,
            Long tradingPrice,
            Long marketTotalAmount
    ) {
        this.indexInfoId = indexInfoId;
        this.baseDate = baseDate;
        this.sourceType = sourceType;
        this.marketPrice = marketPrice;
        this.closingPrice = closingPrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.versus = versus;
        this.fluctuationRate = fluctuationRate;
        this.tradingQuantity = tradingQuantity;
        this.tradingPrice = tradingPrice;
        this.marketTotalAmount = marketTotalAmount;
    }

    // 지수 데이터 ID 반환
    public Long getId() {
        return id;
    }

    // 지수 정보 ID 반환
    public Long getIndexInfoId() {
        return indexInfoId;
    }

    // 기준 날짜 반환
    public LocalDate getBaseDate() {
        return baseDate;
    }

    // 데이터 출처 반환
    public String getSourceType() {
        return sourceType;
    }

    // 시가 반환
    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    // 종가 반환
    public BigDecimal getClosingPrice() {
        return closingPrice;
    }

    // 고가 반환
    public BigDecimal getHighPrice() {
        return highPrice;
    }

    // 저가 반환
    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    // 전일 대비 값 반환
    public BigDecimal getVersus() {
        return versus;
    }

    // 등락률 반환
    public BigDecimal getFluctuationRate() {
        return fluctuationRate;
    }

    // 거래량 반환
    public Long getTradingQuantity() {
        return tradingQuantity;
    }

    // 거래대금 반환
    public Long getTradingPrice() {
        return tradingPrice;
    }

    // 시가총액 반환
    public Long getMarketTotalAmount() {
        return marketTotalAmount;
    }
}