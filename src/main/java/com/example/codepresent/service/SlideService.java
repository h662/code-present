package com.example.codepresent.service;

import com.example.codepresent.dto.SlideRequest;
import com.example.codepresent.dto.SlideResponse;

public interface SlideService {
    SlideResponse createSlide(SlideRequest request);
}
