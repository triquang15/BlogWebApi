package com.devteam.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.devteam.dto.CommentDto;
import com.devteam.dto.UpdateCommentDto;
import com.devteam.entity.Comment;
import com.devteam.entity.Post;
import com.devteam.exception.BlogApiException;
import com.devteam.exception.ResourceNotFoundException;
import com.devteam.mapper.CommentMapper;
import com.devteam.repository.CommentRepository;
import com.devteam.repository.PostRepository;
import com.devteam.service.CommentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;


    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public CommentDto create(Long postId, CommentDto dto) {
        Post post = getPostById(postId);
        Comment comment = CommentMapper.INSTANCE.dtoToEntity(dto);
        comment.setPost(post);
        commentRepository.save(comment);
        return CommentMapper.INSTANCE.entityToDto(comment);
    }

    @Override
    public List<CommentDto> getAllByPost(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(CommentMapper.INSTANCE::entityToDto).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        Post post = getPostById(postId);
        Comment comment = getCommentById(commentId);
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException("Comment does not belong to post!");
        }
        return CommentMapper.INSTANCE.entityToDto(comment);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public CommentDto update(Long postId, Long commentId, UpdateCommentDto dto) {
        Comment comment = getCommentById(commentId);
        Post post = getPostById(postId);
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException("Comment does not belong to post!");
        }
        BeanUtils.copyProperties(dto, comment);
        commentRepository.save(comment);
        return CommentMapper.INSTANCE.entityToDto(comment);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(Long postId, Long commentId) {
        Post post = getPostById(postId);
        Comment comment = getCommentById(commentId);
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException("Comment does not belong to post!");
        }
        commentRepository.deleteById(commentId);
    }


    private Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    }

    private Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", id));
    }
}
