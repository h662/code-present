package com.example.codepresent.service;

import com.example.codepresent.dto.SlideRequest;
import com.example.codepresent.dto.SlideResponse;

import java.util.UUID;

public interface SlideService {
    SlideResponse createSlide(SlideRequest request);
    SlideResponse getSlide(UUID id);
    void deleteSlide(UUID id);
}
