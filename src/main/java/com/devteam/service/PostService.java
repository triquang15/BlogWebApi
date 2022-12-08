package com.devteam.service;

import org.springframework.http.ResponseEntity;

import com.devteam.dto.post.CreatePostDto;
import com.devteam.dto.post.PostDto;
import com.devteam.dto.post.PostResponseDto;
import com.devteam.dto.post.UpdatePostDto;

public interface PostService {
	ResponseEntity<PostDto> create(CreatePostDto dto);

	PostResponseDto getAll(int page, int size, String sort, String sortBy);

	PostDto getById(Long id);

	PostDto update(Long id, UpdatePostDto dto);

	void delete(Long id);
}
