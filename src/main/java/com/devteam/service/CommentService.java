package com.devteam.service;

import java.util.List;

import com.devteam.dto.CommentDto;
import com.devteam.dto.UpdateCommentDto;

public interface CommentService {
	CommentDto create(Long postId, CommentDto dto);

	List<CommentDto> getAllByPost(Long postId);

	CommentDto getCommentById(Long postId, Long commentId);

	CommentDto update(Long postId, Long commentId, UpdateCommentDto dto);

	void delete(Long postId, Long commentId);
}
