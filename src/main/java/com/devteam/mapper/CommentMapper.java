package com.devteam.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.devteam.dto.CommentDto;
import com.devteam.entity.Comment;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    Comment dtoToEntity(CommentDto dto);
    CommentDto entityToDto(Comment comment);

}
