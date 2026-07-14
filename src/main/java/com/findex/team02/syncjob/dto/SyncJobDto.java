package com.findex.team02.syncjob.dto;

import com.findex.team02.global.entity.JobType;
import com.findex.team02.global.entity.ResultType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record SyncJobDto(
        Long id,
        JobType jobType,
        Long indexInfoId,
        LocalDate targetDate,
        String worker,
        LocalDateTime jobTime,
        ResultType result
) {
}
