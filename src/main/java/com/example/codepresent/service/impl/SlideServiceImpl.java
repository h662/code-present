package com.example.codepresent.service.impl;

import com.example.codepresent.domain.Page;
import com.example.codepresent.domain.Series;
import com.example.codepresent.domain.Slide;
import com.example.codepresent.dto.PageRequest;
import com.example.codepresent.dto.PageResponse;
import com.example.codepresent.dto.SlideRequest;
import com.example.codepresent.dto.SlideResponse;
import com.example.codepresent.repository.PageRepository;
import com.example.codepresent.repository.SeriesRepository;
import com.example.codepresent.repository.SlideRepository;
import com.example.codepresent.service.SlideService;
import com.example.codepresent.util.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class SlideServiceImpl implements SlideService {
    private final SlideRepository slideRepository;
    private final PageRepository pageRepository;
    private final SeriesRepository seriesRepository;
    private final S3Util s3Util;


    @Override
    public SlideResponse createSlide(SlideRequest request) {
        Set<Integer> pageNumbers = new HashSet<>();
        for (PageRequest pr : request.getPages()) {
            if(!pageNumbers.add(pr.getPageNumber())) {
                throw new IllegalArgumentException("Duplicate pageNumber: " + pr.getPageNumber());
            }
        }

        Series series = seriesRepository.findById(request.getSeriesId())
                .orElseThrow(() -> new IllegalArgumentException("Series not found: " + request.getSeriesId()));

        Slide slide = Slide.builder()
                .slideTitle(request.getSlideTitle())
                .series(series)
                .seriesOrder(request.getSeriesOrder())
                .build();
        Slide saved = slideRepository.saveAndFlush(slide);
        series.getSlides().add(saved);

        List<Page> pages = request.getPages().stream()
                .map(
                        r -> Page.builder()
                                .slide(saved)
                                .pageNumber(r.getPageNumber())
                                .pageType(r.getPageType())
                                .title(r.getTitle())
                                .description(r.getDescription())
                                .code(r.getCode())
                                .options(r.getOptions())
                                .answer(r.getAnswer())
                                .imageUrl(r.getImageUrl())
                                .build()
                ).toList();

        pageRepository.saveAll(pages);
        saved.getPages().clear();
        saved.getPages().addAll(pages);

        return toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public SlideResponse getSlide(UUID id) {
        Slide slide = slideRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Slide not found: " + id));

        return toResponse(slide);
    }

    private SlideResponse toResponse(Slide slide) {
        List<PageResponse> pageResponses = slide.getPages().stream()
                .map(p -> PageResponse.builder()
                        .id(p.getId())
                        .pageNumber(p.getPageNumber())
                        .pageType(p.getPageType())
                        .title(p.getTitle())
                        .description(p.getDescription())
                        .code(p.getCode())
                        .options(p.getOptions())
                        .answer(p.getAnswer())
                        .imageUrl(s3Util.generatePresignedUrl(p.getImageUrl(), Duration.ofMinutes(30)))
                        .build()
                ).toList();

        return SlideResponse.builder()
                .id(slide.getId())
                .slideTitle(slide.getSlideTitle())
                .createdAt(slide.getCreatedAt())
                .updatedAt(slide.getUpdatedAt())
                .pages(pageResponses)
                .build();
    }
}
