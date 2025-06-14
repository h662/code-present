package com.example.codepresent.service.impl;

import com.example.codepresent.domain.Series;
import com.example.codepresent.domain.Slide;
import com.example.codepresent.dto.SeriesRequest;
import com.example.codepresent.dto.SeriesResponse;
import com.example.codepresent.dto.SlideSummary;
import com.example.codepresent.repository.SeriesRepository;
import com.example.codepresent.service.SeriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    @Transactional(readOnly = true)
    public SeriesResponse getSeries(Long id) {
        Series series = seriesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Series not found: " + id));

        return toResponse(series);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SeriesResponse> getAllSeries() {
        return seriesRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private SeriesResponse toResponse(Series series) {
        List<SlideSummary> slideSummaries = series.getSlides().stream()
                .sorted(Comparator.comparing(Slide::getSeriesOrder))
                .map(s -> SlideSummary.builder()
                        .id(s.getId())
                        .slideTitle(s.getSlideTitle())
                        .seriesOrder(s.getSeriesOrder())
                        .build())
                .collect(Collectors.toList());

        return SeriesResponse.builder()
                .id(series.getId())
                .title(series.getTitle())
                .description(series.getDescription())
                .createdDate(series.getCreatedAt())
                .updatedDate(series.getUpdatedAt())
                .slides(slideSummaries)
                .build();
    }
}
