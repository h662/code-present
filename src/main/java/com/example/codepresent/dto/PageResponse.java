package com.example.codepresent.dto;

import com.example.codepresent.domain.PageType;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResponse {
    private UUID id;
    private int pageNumber;
    private PageType pageType;
    private String title;
    private String description;
    private String code;
    private List<String> options;
    private String answer;
    private String imageUrl;
}
