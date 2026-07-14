package com.findex.team02.syncjob.controller;

import com.findex.team02.syncjob.dto.CursorPageResponse;
import com.findex.team02.syncjob.dto.SyncJobDto;
import com.findex.team02.syncjob.dto.SyncJobSearchRequest;
import com.findex.team02.syncjob.service.SyncJobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sync-jobs")
public class SyncJobController {

    private final SyncJobService syncJobService;

    @GetMapping
    public CursorPageResponse<SyncJobDto> findAll(@ModelAttribute SyncJobSearchRequest request) {
        return syncJobService.findAll(request);
    }
}
