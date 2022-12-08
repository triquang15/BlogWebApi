package com.devteam.dto.post;

import java.time.LocalDateTime;
import java.util.Set;

import com.devteam.dto.CommentDto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto extends CreatePostDto {

    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<CommentDto> comments;
}