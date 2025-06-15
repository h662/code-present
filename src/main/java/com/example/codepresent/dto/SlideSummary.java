package com.example.codepresent.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SlideSummary {
    private UUID id;
    private String slideTitle;
    private Integer seriesOrder;
}

