package com.example.codepresent.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SlideResponse {
    private UUID id;
    private String slideTitle;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<PageResponse> pages;
}
