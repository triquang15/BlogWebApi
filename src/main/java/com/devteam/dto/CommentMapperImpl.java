package com.devteam.dto;

import javax.annotation.processing.Generated;

import com.devteam.entity.Comment;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-08T12:17:49+0700",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 1.4.50.v20210914-1429, environment: Java 17.0.1 (Eclipse Adoptium)"
)
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment dtoToEntity(CommentDto dto) {
        if ( dto == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setEmail( dto.getEmail() );
        comment.setId( dto.getId() );
        comment.setMessage( dto.getMessage() );
        comment.setName( dto.getName() );

        return comment;
    }

    @Override
    public CommentDto entityToDto(Comment comment) {
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
}

