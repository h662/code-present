package com.example.codepresent.controller;

import com.example.codepresent.dto.SlideRequest;
import com.example.codepresent.dto.SlideResponse;
import com.example.codepresent.service.SlideService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/slides")
@RequiredArgsConstructor
public class SlideController {
    private final SlideService slideService;

    @Value("${app.admin.password}")
    private String adminPassword;

    @PostMapping
    public ResponseEntity<SlideResponse> createSlide(
            @RequestHeader("X-ADMIN-PASSWORD") String password,
            @RequestBody SlideRequest request
    ) {
        if(!adminPassword.equals(password)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        SlideResponse response = slideService.createSlide(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
