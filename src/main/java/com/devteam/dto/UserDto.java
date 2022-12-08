package com.devteam.dto;

import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

import com.devteam.entity.Role;


@Data
public class UserDto {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private Set<String> roles;


    public void setRoles(Set<Role> roles) {
        this.roles = roles.stream().map(Role::getRole).collect(Collectors.toSet());
    }

}
