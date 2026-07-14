package com.findex.team02.syncjob.service;

import com.findex.team02.global.entity.SyncJob;
import com.findex.team02.syncjob.dto.CursorPageResponse;
import com.findex.team02.syncjob.dto.SyncJobDto;
import com.findex.team02.syncjob.dto.SyncJobSearchRequest;
import com.findex.team02.syncjob.repository.SyncJobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SyncJobService {

    private  final SyncJobRepository syncJobRepository;

    public CursorPageResponse<SyncJobDto> findAll(SyncJobSearchRequest  request) {

        Specification<SyncJob> spec= Specification.unrestricted();

        if (request.jobType() != null)
            spec = spec.and((Root, Query, cb) -> cb.equal(Root.get("jobType"), request.jobType()));

        if (request.indexInfoId() != null)
            spec = spec.and((Root, Query, cb) -> cb.equal(Root.get("indexInfoId"), request.indexInfoId()));

        if (request.worker() != null && !request.worker().isBlank()) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("worker"), "%" + request.worker() + "%"));
        }

        if (request.status() != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("result"), request.status()));
        }

        if (request.baseDateFrom() != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.greaterThanOrEqualTo(root.get("targetDate"), request.baseDateFrom()));
        }

        if (request.baseDateTo() != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.lessThanOrEqualTo(root.get("targetDate"), request.baseDateTo()));
        }

        if (request.jobTimeFrom() != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.greaterThanOrEqualTo(root.get("jobTime"), request.jobTimeFrom()));
        }

        if (request.jobTimeTo() != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.lessThanOrEqualTo(root.get("jobTime"), request.jobTimeTo()));
        }

        String sortField = request.sortField() == null ? "jobTime" : request.sortField();
        String sortDirection = request.sortDirection() == null ? "desc" : request.sortDirection();
        int size = request.size() == null ? 10 : request.size();

        //대소문자 무시하는 방향으로 equals -> equalsIgonoreCase로 변경
        Sort.Direction direction = sortDirection.equalsIgnoreCase("asc")
                ? Sort.Direction.ASC
                : Sort.Direction.DESC;

        Pageable pageable = PageRequest.of(0,size,Sort.by(direction, sortField));

        Page<SyncJob> page = syncJobRepository.findAll(spec, pageable);

        List<SyncJobDto> content = page.getContent()
                .stream()
                .map(this::toDto)
                .toList();

        return new CursorPageResponse<>(
                content,
                null,
                content.isEmpty() ? null : content.get(content.size() - 1).id(),
                page.getSize(),
                page.getTotalElements(),
                page.hasNext()
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
