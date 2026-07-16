package com.findex.team02.autosync.service;

import com.findex.team02.autosync.dto.request.AutoSyncConfigSearchRequest;
import com.findex.team02.autosync.dto.request.AutoSyncConfigUpdateRequest;
import com.findex.team02.autosync.dto.response.AutoSyncConfigDto;
import com.findex.team02.autosync.dto.response.CursorPageResponseAutoSyncConfigDto;
import com.findex.team02.autosync.entity.AutoSyncConfig;
import com.findex.team02.autosync.mapper.AutoSyncConfigMapper;
import com.findex.team02.autosync.repository.AutoSyncConfigRepository;
import com.findex.team02.global.exception.ResourceNotFoundException;
import com.findex.team02.indexinfo.dto.response.CursorPageResponseIndexInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BasicAutoSyncConfigService implements AutoSyncConfigService{

    private final AutoSyncConfigRepository autoSyncConfigRepository;
    private final AutoSyncConfigMapper autoSyncConfigMapper;

    @Override
    @Transactional(readOnly = true)
    public CursorPageResponseAutoSyncConfigDto findAll(AutoSyncConfigSearchRequest request) {
        int size = request.size();

        List<AutoSyncConfig> configs = autoSyncConfigRepository.findAllByCondition(request);

        List<AutoSyncConfigDto> content = autoSyncConfigMapper.toDto(configs);

        long totalElements = autoSyncConfigRepository.countTotalElements(request);

        // 조회 결과가 없는 경우 빈 리스트 처리
        if (configs.isEmpty()) {
            return new CursorPageResponseAutoSyncConfigDto(
                    List.of(),
                    null,
                    null,
                    size,
                    0L,
                    false
            );
        }

        boolean hasNext = configs.size() > size;

        if (hasNext) {
            configs = configs.subList(0, size);
            content = content.subList(0, size);
        }

        AutoSyncConfig last = configs.get(configs.size() - 1);

        String nextCursor = switch (request.sortField() == null ? "" : request.sortField()) {
            case "enabled" -> String.valueOf(last.getEnabled());
            default -> last.getIndexInfo().getIndexName();
        };

        return new CursorPageResponseAutoSyncConfigDto(
                content,
                nextCursor,
                last.getId(),
                size,
                totalElements,
                hasNext
        );

    }

    @Override
    public AutoSyncConfigDto update(Long id, AutoSyncConfigUpdateRequest request) {
        AutoSyncConfig config = autoSyncConfigRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("자동 연동 설정을 찾을 수 없습니다."));

        config.updateEnabled(request.enabled());

        return toDto(config);
    }

    private AutoSyncConfigDto toDto(AutoSyncConfig config) {
        return new AutoSyncConfigDto(
                config.getId(),
                config.getIndexInfo().getId(),
                config.getIndexInfo().getIndexName(),
                config.getIndexInfo().getIndexClassification(),
                config.getEnabled()
        );
    }

}

