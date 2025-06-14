package com.example.codepresent.controller;

import com.example.codepresent.dto.SeriesRequest;
import com.example.codepresent.dto.SeriesResponse;
import com.example.codepresent.dto.SlideRequest;
import com.example.codepresent.dto.SlideResponse;
import com.example.codepresent.service.SeriesService;
import com.example.codepresent.service.SlideService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/series")
@RequiredArgsConstructor
public class SeriesController {
    private final SeriesService seriesService;

    @Value("${app.admin.password}")
    private String adminPassword;

    @PostMapping
    public ResponseEntity<SeriesResponse> createSeries(
            @RequestHeader("X-ADMIN-PASSWORD") String password,
            @RequestBody SeriesRequest request
    ) {
        if(!adminPassword.equals(password)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        SeriesResponse response = seriesService.createSeries(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
