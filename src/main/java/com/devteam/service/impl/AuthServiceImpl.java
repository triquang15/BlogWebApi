package com.devteam.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.devteam.dto.LoginDto;
import com.devteam.dto.LoginResponseDto;
import com.devteam.dto.RegisterDto;
import com.devteam.dto.UserDto;
import com.devteam.dto.UserMapper;
import com.devteam.entity.Role;
import com.devteam.entity.User;
import com.devteam.repository.RoleRepository;
import com.devteam.repository.UserRepository;
import com.devteam.security.UserDetailsImpl;
import com.devteam.security.jwt.JwtUtils;
import com.devteam.service.AuthService;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;


    @Override
	public ResponseEntity<LoginResponseDto> login(LoginDto dto) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsernameOrEmail(), dto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        String token = jwtUtils.generateJwtToken(auth);
        UserDetailsImpl principal = (UserDetailsImpl) auth.getPrincipal();
        UserDto user = UserMapper.INSTANCE.userDetailsToUserDto(principal);
        return new ResponseEntity<>(new LoginResponseDto(user, token), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> register(RegisterDto dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            return new ResponseEntity<>("Email is already registered!", HttpStatus.BAD_REQUEST);
        }
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            return new ResponseEntity<>("Username is already registered!", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        BeanUtils.copyProperties(dto, user);
        user.setFirstName(dto.getFirstname());
        user.setLastName(dto.getLastname());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        addRolesToUser(user);
        userRepository.save(user);
        return new ResponseEntity<>("User registered!", HttpStatus.CREATED);
    }

    @Override
    public void createRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Optional<Role> findByRole(String roleType) {
        return roleRepository.findByRole(roleType);
    }

    @Override
    public void addRoleToUser(String email, String roleType) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User not found with email:" + email)
        );
        Role role = roleRepository.findByRole(roleType).orElse(null);
        user.setRoles(Collections.singleton(role));
    }


    private void addRolesToUser(User user) {
        Set<Role> roleArr = new HashSet<>();
        roleArr.add(roleRepository.findByRole("ROLE_USER").orElse(null));
        user.setRoles(new HashSet<>(roleArr));
    }

}
