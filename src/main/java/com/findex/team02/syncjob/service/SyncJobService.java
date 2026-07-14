package com.findex.team02.syncjob.service;

import com.findex.team02.global.entity.SyncJob;
import com.findex.team02.syncjob.dto.SyncJobDto;
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

    public List<SyncJobDto> findAll() {

        return syncJobRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    private SyncJobDto toDto(SyncJob syncJob) {
        return new SyncJobDto(syncJob.getId(),
                syncJob.getJopType(),
                syncJob.getIndexInfoId(),
                syncJob.getTargetDate().atStartOfDay(),
                syncJob.getWorker(),
                syncJob.getJobTime(),
                syncJob.getResult());
    }
}
