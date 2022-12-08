package com.devteam.dto.post;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;

import com.devteam.dto.CommentDto;
import com.devteam.entity.Comment;
import com.devteam.entity.Post;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-08T12:17:50+0700",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 1.4.50.v20210914-1429, environment: Java 17.0.1 (Eclipse Adoptium)"
)
public class PostMapperImpl implements PostMapper {

    @Override
    public PostDto entityToDto(Post post) {
        if ( post == null ) {
            return null;
        }

        PostDto postDto = new PostDto();

        postDto.setContent( post.getContent() );
        postDto.setDescription( post.getDescription() );
        postDto.setTitle( post.getTitle() );
        postDto.setComments( commentSetToCommentDtoSet( post.getComments() ) );
        postDto.setCreatedAt( post.getCreatedAt() );
        postDto.setId( post.getId() );
        postDto.setUpdatedAt( post.getUpdatedAt() );

        return postDto;
    }

    @Override
    public Post dtoToEntity(PostDto dto) {
        if ( dto == null ) {
            return null;
        }

        Post post = new Post();

        post.setComments( commentDtoSetToCommentSet( dto.getComments() ) );
        post.setContent( dto.getContent() );
        post.setCreatedAt( dto.getCreatedAt() );
        post.setDescription( dto.getDescription() );
        post.setId( dto.getId() );
        post.setTitle( dto.getTitle() );
        post.setUpdatedAt( dto.getUpdatedAt() );

        return post;
    }

    protected CommentDto commentToCommentDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentDto commentDto = new CommentDto();

        commentDto.setId( comment.getId() );
        commentDto.setMessage( comment.getMessage() );
        commentDto.setEmail( comment.getEmail() );
        commentDto.setName( comment.getName() );

        return commentDto;
    }

    protected Set<CommentDto> commentSetToCommentDtoSet(Set<Comment> set) {
        if ( set == null ) {
            return null;
        }

        Set<CommentDto> set1 = new LinkedHashSet<CommentDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Comment comment : set ) {
            set1.add( commentToCommentDto( comment ) );
        }

        return set1;
    }

    protected Comment commentDtoToComment(CommentDto commentDto) {
        if ( commentDto == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setEmail( commentDto.getEmail() );
        comment.setId( commentDto.getId() );
        comment.setMessage( commentDto.getMessage() );
        comment.setName( commentDto.getName() );

        return comment;
    }

    protected Set<Comment> commentDtoSetToCommentSet(Set<CommentDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Comment> set1 = new LinkedHashSet<Comment>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( CommentDto commentDto : set ) {
            set1.add( commentDtoToComment( commentDto ) );
        }

        return set1;
    }
}

