package com.findex.team02.indexdata.service;

import com.findex.team02.indexdata.dto.request.IndexDataCreateRequest;
import com.findex.team02.indexdata.dto.request.IndexDataUpdateRequest;
import com.findex.team02.indexdata.dto.response.CursorPageResponseIndexDataDto;
import com.findex.team02.indexdata.dto.response.IndexDataDto;
import com.findex.team02.indexdata.entity.IndexData;
import com.findex.team02.indexdata.entity.IndexInfo;
import com.findex.team02.indexdata.entity.SourceType;
import com.findex.team02.indexdata.mapper.IndexDataMapper;
import com.findex.team02.indexdata.repository.IndexDataRepository;
import com.findex.team02.indexdata.repository.IndexInfoRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BasicIndexDataService implements IndexDataService{

  private final IndexDataRepository indexDataRepository;
  private final IndexInfoRepository indexInfoRepository;
  private final IndexDataMapper indexDataMapper;


  @Override
  public IndexDataDto create(IndexDataCreateRequest request) {

    IndexInfo indexInfo = indexInfoRepository.findById(request.indexInfoId())
        .orElseThrow();

    IndexData indexData = new IndexData(
        indexInfo,
        request.baseDate(),
        SourceType.USER,
        request.marketPrice(),
        request.closingPrice(),
        request.highPrice(),
        request.lowPrice(),
        request.versus(),
        request.fluctuationRate(),
        request.tradingQuantity(),
        request.tradingPrice(),
        request.marketTotalAmount()
    );

    IndexData saved = indexDataRepository.save(indexData);

    return indexDataMapper.toDto(saved);
  }

  @Override
  public IndexDataDto update(Long id, IndexDataUpdateRequest request) {
    IndexData indexData = indexDataRepository.findById(id)
        .orElseThrow();

    indexData.update(
        request.marketPrice(),
        request.closingPrice(),
        request.highPrice(),
        request.lowPrice(),
        request.versus(),
        request.fluctuationRate(),
        request.tradingQuantity(),
        request.tradingPrice(),
        request.marketTotalAmount()
    );

    return indexDataMapper.toDto(indexData);
  }

  @Override
  public void delete(Long id) {

  }

  @Override
  public IndexDataDto findById(Long id) {
    return null;
  }

  @Override
  public CursorPageResponseIndexDataDto findAll(
      Long indexInfoId,
      LocalDate startDate,
      LocalDate endDate,
      Long idAfter,
      String cursor,
      String sortField,
      String sortDirection,
      Integer size
  ) {
    return null;
  }
}
