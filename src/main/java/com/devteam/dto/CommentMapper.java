package com.devteam.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.devteam.entity.Comment;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    Comment dtoToEntity(CommentDto dto);
    CommentDto entityToDto(Comment comment);

}
