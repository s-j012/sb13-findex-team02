package com.findex.team02.global.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 외부 API 연동 작업의 실행 이력을 저장하는 Entity입니다.
 *
 * 지수 정보 연동, 지수 데이터 연동, 자동 배치 실행 결과를
 * 성공/실패 여부와 함께 sync_jobs 테이블에 기록합니다.
 */
@Getter
@Entity
@Table(name = "sync_job")
public class SyncJob extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "jop_type", nullable = false)
    private JopType jopType;

    @Column(name = "index_info_id", nullable = false)
    private Long indexInfoId;


    @Column(name = "target_date")
    private LocalDate targetDate;

    @Column(name = "worker", nullable = false)
    private String worker;

    @Column(name = "job_time", nullable = false)
    private LocalDateTime jobTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "result")
    private ResultType result;


}
