package com.example.codepresent.service;

import com.example.codepresent.dto.SlideRequest;
import com.example.codepresent.dto.SlideResponse;

import java.util.List;

public interface SlideService {
    SlideResponse createSlide(SlideRequest request);
    SlideResponse getSlide(Long id);
    List<SlideResponse> getAllSlides();
}
