package com.example.codepresent.service;


import com.example.codepresent.dto.SeriesRequest;
import com.example.codepresent.dto.SeriesResponse;

import java.util.List;

public interface SeriesService {
    SeriesResponse createSeries(SeriesRequest request);
    SeriesResponse getSeries(Long id);
    List<SeriesResponse> getAllSeries();
}
