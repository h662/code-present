package com.example.codepresent.service;


import com.example.codepresent.dto.SeriesRequest;
import com.example.codepresent.dto.SeriesResponse;

public interface SeriesService {
    SeriesResponse createSeries(SeriesRequest request);
}
