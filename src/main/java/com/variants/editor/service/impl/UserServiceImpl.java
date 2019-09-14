package com.variants.editor.service.impl;

import java.util.Collections;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import com.variants.editor.domain.Role;
import com.variants.editor.domain.RoleName;
import com.variants.editor.domain.User;
import com.variants.editor.exception.NotFoundException;
import com.variants.editor.exception.ValidationException;
import com.variants.editor.repository.RoleRepository;
import com.variants.editor.repository.UserRepository;
import com.variants.editor.security.UserPrincipal;
import com.variants.editor.security.jwt.JwtTokenUtil;
import com.variants.editor.service.UserService;
import com.variants.editor.web.request.LoginRequest;
import com.variants.editor.web.request.RegisterUserRequest;
import com.variants.editor.web.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(AuthenticationManager authenticationManager,
                           JwtTokenUtil jwtTokenUtil,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String uuid) {
        User user = getByUUID(uuid);

        return UserPrincipal.create(user);
    }

    public User getByUUID(String uuid) {
        return userRepository.findByUuid(uuid)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with UUID " + uuid));
    }

    public LoginResponse authenticateUser(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUuid(), request.getPassword()));

        UserDetails userDetails = loadUserByUsername(request.getUuid());

        String token = jwtTokenUtil.generateToken(userDetails);

        return new LoginResponse(token);
    }

    public User registerUser(RegisterUserRequest request) {
        validatePasswordFormat(request.getPassword());

        User user = new User();

        Stream.of(user)
                .forEach(u -> {
                    u.setName(request.getName());
                    u.setPassword(passwordEncoder.encode(request.getPassword()));
                    u.setUuid(generateUUID());
                    u.setRoles(Collections.singleton(getRole()));

                    userRepository.save(u);
                });

        return user;
    }

    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    private void validatePasswordFormat(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[0-9]).{8,60}$");
        boolean result = pattern.matcher(password).matches();

        if (!result) {
            throw new ValidationException("Password must be between 8-60 char length and contains at least 1 digit");
        }
    }

    private String generateUUID() {
        return UUID.randomUUID().toString().toUpperCase();
    }

    private Role getRole() {
        return roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new NotFoundException("Role not found"));
    }
}