package com.example.codepresent.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SlideSummary {
    private Long id;

    private String slideTitle;

    private Integer seriesOrder;
}

