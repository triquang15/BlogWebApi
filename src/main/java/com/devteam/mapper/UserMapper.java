package com.devteam.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.devteam.dto.RegisterDto;
import com.devteam.dto.UserDto;
import com.devteam.entity.User;
import com.devteam.security.UserDetailsImpl;

@Mapper
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	RegisterDto dtoToEntity(User user);

	User entityToDto(RegisterDto dto);

	@Mapping(target = "user.roles", ignore = true)
	UserDto userDetailsToUserDto(UserDetailsImpl user);

}
