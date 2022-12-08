package com.devteam.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class RegisterDto {

    @NotEmpty(message = "Firstname is required!")
    private String firstname;

    @NotEmpty(message = "Lastname is required!")
    private String lastname;

    @NotEmpty(message = "Email is required!")
    @Email(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}",
    flags = Pattern.Flag.CASE_INSENSITIVE,
    message = "Email must be a well-formed email address!")
    private String email;

    @NotEmpty(message = "Username is required!")
    private String username;

    @NotEmpty
    @NotEmpty(message = "Password is required!")
    private String password;
    
    private void setFirstname(String firstname) {
        this.firstname = firstname.trim();
    }

    private void setLastname(String lastname) {
        this.lastname = lastname.trim();
    }

    private void setEmail(String email) {
        this.email = email.toLowerCase().trim();
    }

    private void setUsername(String username) {
        this.username = username.trim();
    }

}
