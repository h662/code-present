package com.example.codepresent.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SlideRequest {
    private String slideTitle;
    private List<PageRequest> pages;
    private UUID seriesId;
    private Integer seriesOrder;
}
