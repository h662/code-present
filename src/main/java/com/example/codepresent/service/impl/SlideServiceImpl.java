package com.example.codepresent.service.impl;

import com.example.codepresent.domain.Page;
import com.example.codepresent.domain.Slide;
import com.example.codepresent.dto.PageResponse;
import com.example.codepresent.dto.SlideRequest;
import com.example.codepresent.dto.SlideResponse;
import com.example.codepresent.repository.PageRepository;
import com.example.codepresent.repository.SlideRepository;
import com.example.codepresent.service.SlideService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SlideServiceImpl implements SlideService {
    private final SlideRepository slideRepository;
    private final PageRepository pageRepository;


    @Override
    public SlideResponse createSlide(SlideRequest request) {
        Slide slide = Slide.builder()
                .slideCode(request.getSlideCode())
                .slideTitle(request.getSlideTitle())
                .build();
        Slide saved = slideRepository.save(slide);

        List<Page> pages = request.getPages().stream()
                .map(
                        r -> Page.builder()
                                .slide(saved)
                                .pageNumber(r.getPageNumber())
                                .pageType(r.getPageType())
                                .contentText(r.getContentText())
                                .imageUrl(r.getImageUrl())
                                .build()
                ).toList();

        pageRepository.saveAll(pages);

        saved.setPages(pages);
        return toResponse(saved);
    }

    private SlideResponse toResponse(Slide slide) {
        List<PageResponse> pageResponses = slide.getPages().stream()
                .map(p -> PageResponse.builder()
                        .id(p.getId())
                        .pageNumber(p.getPageNumber())
                        .pageType(p.getPageType())
                        .contentText(p.getContentText())
                        .imageUrl(p.getImageUrl())
                        .build()
                ).toList();

        return SlideResponse.builder()
                .id(slide.getId())
                .slideCode(slide.getSlideCode())
                .slideTitle(slide.getSlideTitle())
                .createdAt(slide.getCreatedAt())
                .updatedAt(slide.getUpdatedAt())
                .pages(pageResponses)
                .build();
    }
}
