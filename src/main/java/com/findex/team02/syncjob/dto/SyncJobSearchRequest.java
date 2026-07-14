package com.findex.team02.syncjob.dto;

import com.findex.team02.global.entity.JobType;
import com.findex.team02.global.entity.ResultType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record SyncJobSearchRequest(
        JobType jobType,
        Long indexInfoId,
        LocalDate baseDateFrom,
        LocalDate baseDateTo,
        String worker,
        LocalDateTime jobTimeFrom,
        LocalDateTime jobTimeTo,
        ResultType status,
        Long idAfter,
        String cursor,
        String sortField,
        String sortDirection,
        Integer size
) {
}
