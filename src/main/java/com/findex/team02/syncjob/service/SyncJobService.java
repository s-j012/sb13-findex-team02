package com.findex.team02.syncjob.service;

import com.findex.team02.global.entity.SyncJob;
import com.findex.team02.syncjob.dto.CursorPageResponse;
import com.findex.team02.syncjob.dto.SyncJobDto;
import com.findex.team02.syncjob.dto.SyncJobSearchRequest;
import com.findex.team02.syncjob.repository.SyncJobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SyncJobService {

    private  final SyncJobRepository syncJobRepository;

    public CursorPageResponse<SyncJobDto> findAll(SyncJobSearchRequest  request) {
        List<SyncJobDto> content = syncJobRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
        Long nextIdAfter = content.isEmpty()
                ? null
                : content.get(content.size() - 1).id();

        return new CursorPageResponse<>(
                content,
                null,
                nextIdAfter,
                content.size(),
                content.size(),
                false
        );
    }

    private SyncJobDto toDto(SyncJob syncJob) {
        return new SyncJobDto(syncJob.getId(),
                syncJob.getJobType(),
                syncJob.getIndexInfoId(),
                syncJob.getTargetDate(),
                syncJob.getWorker(),
                syncJob.getJobTime(),
                syncJob.getResult());
    }
}
