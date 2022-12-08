package com.devteam.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PostResponseDto {

    private List<PostDto> content;
    private int page;
    private int size;
    private Long totalElements;
    private int totalPages;
    private boolean last;

}
