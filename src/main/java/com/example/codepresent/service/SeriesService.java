package com.example.codepresent.service;


import com.example.codepresent.dto.SeriesRequest;
import com.example.codepresent.dto.SeriesResponse;

import java.util.List;
import java.util.UUID;

public interface SeriesService {
    SeriesResponse createSeries(SeriesRequest request);
    SeriesResponse getSeries(UUID id);
    List<SeriesResponse> getAllSeries();
}
