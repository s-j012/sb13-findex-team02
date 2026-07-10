package com.findex.team02.indexinfo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

// index_infos 테이블과 매핑되는 JPA Entity
// 지수의 기본 정보, 분류, 기준 지수, 즐겨찾기 여부 등을 저장하는 객체
@Entity
@Table(name = "index_infos")
public class IndexInfo {

    // 지수 정보 고유 ID
    // DB의 id 컬럼과 매핑
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 지수 분류
    // 예: KRX시리즈, 테마지수 등
    @Column(name = "index_classification", nullable = false)
    private String indexClassification;

    // 지수명
    // 예: KRX 300 정보기술, KRX 정보기술 등
    @Column(name = "index_name", nullable = false)
    private String indexName;

    // 지수에 포함된 종목 수
    @Column(name = "employed_items_count", nullable = false)
    private Integer employedItemsCount;

    // 기준 시점
    // 해당 지수가 어떤 날짜를 기준으로 만들어졌는지 나타냄
    @Column(name = "base_point_in_time", nullable = false)
    private LocalDate basePointInTime;

    // 기준 지수
    // 기준 시점의 지수 값
    @Column(name = "base_index", nullable = false, precision = 10, scale = 2)
    private BigDecimal baseIndex;

    // 데이터 출처
    // 예: OPEN_API, USER
    @Column(name = "source_type", nullable = false)
    private String sourceType;

    // 즐겨찾기 여부
    // 관심 지수 성과 조회에서 사용됨
    @Column(name = "favorite", nullable = false)
    private Boolean favorite = false;

    // 생성 일시
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // 수정 일시
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // JPA 기본 생성자
    // JPA가 Entity 객체를 생성할 때 필요함
    protected IndexInfo() {
    }

    // 테스트 데이터 생성이나 직접 객체 생성 시 사용할 생성자
    public IndexInfo(
            String indexClassification,
            String indexName,
            Integer employedItemsCount,
            LocalDate basePointInTime,
            BigDecimal baseIndex,
            String sourceType,
            Boolean favorite
    ) {
        this.indexClassification = indexClassification;
        this.indexName = indexName;
        this.employedItemsCount = employedItemsCount;
        this.basePointInTime = basePointInTime;
        this.baseIndex = baseIndex;
        this.sourceType = sourceType;
        this.favorite = favorite != null ? favorite : false;
    }

    // Entity가 처음 저장되기 전에 실행되는 메서드
    // 생성일과 수정일을 현재 시간으로 설정
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Entity가 수정되기 전에 실행되는 메서드
    // 수정일을 현재 시간으로 갱신
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // 지수 정보 ID 반환
    public Long getId() {
        return id;
    }

    // 지수 분류 반환
    public String getIndexClassification() {
        return indexClassification;
    }

    // 지수명 반환
    public String getIndexName() {
        return indexName;
    }

    // 포함 종목 수 반환
    public Integer getEmployedItemsCount() {
        return employedItemsCount;
    }

    // 기준 시점 반환
    public LocalDate getBasePointInTime() {
        return basePointInTime;
    }

    // 기준 지수 반환
    public BigDecimal getBaseIndex() {
        return baseIndex;
    }

    // 데이터 출처 반환
    public String getSourceType() {
        return sourceType;
    }

    // 즐겨찾기 여부 반환
    public Boolean getFavorite() {
        return favorite;
    }

    // 생성 일시 반환
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // 수정 일시 반환
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    // 즐겨찾기 여부를 boolean 값으로 반환
    // null 안전 처리를 위해 Boolean.TRUE.equals 사용
    public boolean isFavorite() {
        return Boolean.TRUE.equals(favorite);
    }
}

