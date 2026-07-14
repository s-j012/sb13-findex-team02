package com.findex.team02.indexInfo.service;

import com.findex.team02.indexInfo.dto.request.IndexInfoSearchRequest;
import com.findex.team02.indexInfo.dto.response.CursorPageResponseIndexInfoDto;
import com.findex.team02.indexInfo.dto.response.IndexInfoDto;
import com.findex.team02.indexInfo.entity.IndexInfo;
import com.findex.team02.indexInfo.mapper.IndexInfoMapper;
import com.findex.team02.indexInfo.repository.IndexInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BasicIndexInfoService implements IndexInfoService {

    private final IndexInfoRepository indexInfoRepository;
    private final IndexInfoMapper indexInfoMapper;

    @Override
    public CursorPageResponseIndexInfoDto getIndexInfos(IndexInfoSearchRequest request) {
        int size = request.size();

        List<IndexInfo> indexInfos = indexInfoRepository.findAllByCondition(request);

        boolean hasNext = indexInfos.size() > size;

        if (hasNext) {
            indexInfos.remove(size);
        }

        List<IndexInfoDto> content = indexInfoMapper.toDto(indexInfos);

        IndexInfo last = indexInfos.get(indexInfos.size() - 1);

        return new CursorPageResponseIndexInfoDto(
                content,
                last.getId().toString(),
                last.getId(),
                size,
                (long) indexInfos.size(),
                hasNext
        );
    }

}
