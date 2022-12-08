package com.devteam.dto.post;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.devteam.entity.Post;

@Mapper
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(target = "post.comments", ignore = true)
    PostDto entityToDto(Post post);
    Post dtoToEntity(PostDto dto);
}
