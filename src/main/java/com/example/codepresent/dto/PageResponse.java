package com.example.codepresent.dto;

import com.example.codepresent.domain.PageType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResponse {
    private Long id;
    private int pageNumber;
    private PageType pageType;
    private String contentText;
    private String imageUrl;
}
