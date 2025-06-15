package com.example.codepresent.dto;

import com.example.codepresent.domain.PageType;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageRequest {
    private int pageNumber;
    private PageType pageType;
    private String title;
    private String description;
    private String code;
    private List<String> options;
    private String answer;
    private String imageUrl;
}
