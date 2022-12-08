package com.devteam.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devteam.dto.CommentDto;
import com.devteam.dto.UpdateCommentDto;
import com.devteam.service.CommentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping("/posts/{postId}/comments")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<CommentDto> create(@PathVariable(value = "postId") Long postId, @Valid @RequestBody CommentDto dto) {
        CommentDto comment = commentService.create(postId, dto);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getAllByPost(@PathVariable(value = "postId") Long postId) {
        return commentService.getAllByPost(postId);
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(
            @PathVariable(value = "postId") Long postId,
            @PathVariable(value = "commentId") Long commentId) {
        return new ResponseEntity<>(commentService.getCommentById(postId, commentId), HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<CommentDto> update(
            @PathVariable(value = "commentId") Long commentId,
            @PathVariable(value = "postId") Long postId,
            @Valid @RequestBody UpdateCommentDto dto) {
        CommentDto comment = commentService.update(postId, commentId, dto);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<String> delete(
            @PathVariable(value = "commentId") Long commentId,
            @PathVariable(value = "postId") Long postId) {
        commentService.delete(postId, commentId);
        return new ResponseEntity<>("Comment deleted successfully!", HttpStatus.OK);
    }
}
