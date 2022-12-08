package com.devteam.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.devteam.dto.post.CreatePostDto;
import com.devteam.dto.post.PostDto;
import com.devteam.dto.post.PostResponseDto;
import com.devteam.dto.post.UpdatePostDto;
import com.devteam.entity.Post;
import com.devteam.exception.BlogApiException;
import com.devteam.exception.ResourceNotFoundException;
import com.devteam.mapper.PostMapper;
import com.devteam.repository.CommentRepository;
import com.devteam.repository.PostRepository;
import com.devteam.service.PostService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;


    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PostDto> create(CreatePostDto dto) {
        findByTitle(dto.getTitle());
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setDescription(dto.getDescription());
        post.setContent(dto.getContent());
        postRepository.save(post);
        return new ResponseEntity<>(PostMapper.INSTANCE.entityToDto(post), HttpStatus.CREATED);
    }

    @Override
    public PostResponseDto getAll(int page, int size, String sort, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(sort), sortBy);
        Page<Post> posts = postRepository.findAll(pageable);
        List<PostDto> content = posts.stream().map(PostMapper.INSTANCE::entityToDto).collect(Collectors.toList());
        return new PostResponseDto(
                content,
                posts.getNumber(),
                posts.getSize(),
                posts.getTotalElements(),
                posts.getTotalPages(),
                posts.isLast()
        );
    }

    @Override
    public PostDto getById(Long id) {
        Post post = getEntityById(id);
        return PostMapper.INSTANCE.entityToDto(post);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public PostDto update(Long id, UpdatePostDto dto) {
        findByTitle(dto.getTitle());
        Post post = getEntityById(id);
        if (post.getTitle().equals(dto.getTitle())) {
            throw new BlogApiException("Title already exist! Think new one.");
        }
        BeanUtils.copyProperties(dto, post);
        postRepository.save(post);
        return PostMapper.INSTANCE.entityToDto(post);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(Long id) {
        Post post = getEntityById(id);
        commentRepository.deleteAll(post.getComments());
        postRepository.deleteById(post.getId());
    }


    private Post getEntityById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    }

    private void findByTitle(String title) {
        if (postRepository.findByTitle(title).isPresent())
            throw new BlogApiException("Title already exist! Think new one.");
    }

}
