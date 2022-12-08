package com.devteam.dto;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;

import com.devteam.entity.Role;
import com.devteam.entity.User;
import com.devteam.security.UserDetailsImpl;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-08T12:17:50+0700",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 1.4.50.v20210914-1429, environment: Java 17.0.1 (Eclipse Adoptium)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public RegisterDto dtoToEntity(User user) {
        if ( user == null ) {
            return null;
        }

        RegisterDto registerDto = new RegisterDto();

        registerDto.setPassword( user.getPassword() );

        return registerDto;
    }

    @Override
    public User entityToDto(RegisterDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setEmail( dto.getEmail() );
        user.setFirstName( dto.getFirstname() );
        user.setLastName( dto.getLastname() );
        user.setPassword( dto.getPassword() );
        user.setUsername( dto.getUsername() );

        return user;
    }

    @Override
    public UserDto userDetailsToUserDto(UserDetailsImpl user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setEmail( user.getEmail() );
        userDto.setId( user.getId() );
        userDto.setLastname( user.getLastname() );
        userDto.setFirstname( user.getFirstname() );
        userDto.setUsername( user.getUsername() );
        Set<Role> set = user.getRoles();
        if ( set != null ) {
            userDto.setRoles( new LinkedHashSet<Role>( set ) );
        }

        return userDto;
    }
}

