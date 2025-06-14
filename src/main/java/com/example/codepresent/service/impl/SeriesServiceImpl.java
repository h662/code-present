package com.example.codepresent.service.impl;

import com.example.codepresent.domain.Series;
import com.example.codepresent.dto.SeriesRequest;
import com.example.codepresent.dto.SeriesResponse;
import com.example.codepresent.repository.SeriesRepository;
import com.example.codepresent.service.SeriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SeriesServiceImpl implements SeriesService {
    private final SeriesRepository seriesRepository;

    @Override
    public SeriesResponse createSeries(SeriesRequest request) {
        Series series = Series.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .build();
        Series saved = seriesRepository.save(series);

        return toResponse(saved);
    }

    private SeriesResponse toResponse(Series series) {

        return SeriesResponse.builder()
                .id(series.getId())
                .title(series.getTitle())
                .description(series.getDescription())
                .createdDate(series.getCreatedAt())
                .updatedDate(series.getUpdatedAt())
                .build();
    }
}
