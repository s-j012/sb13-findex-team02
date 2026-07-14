package com.findex.team02.syncjob.dto;

import com.findex.team02.global.entity.JopType;
import com.findex.team02.global.entity.ResultType;

import java.time.LocalDateTime;

public record SyncJobDto(
        Long id,
        JopType jobType,
        Long indexInfoId,
        LocalDateTime targetDate,
        String worker,
        LocalDateTime jobTime,
        ResultType result
) {
}
