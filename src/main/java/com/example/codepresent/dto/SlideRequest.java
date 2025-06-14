package com.example.codepresent.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SlideRequest {
    private Long slideCode;
    private String slideTitle;
    private List<PageRequest> pages;
}
