package com.variants.editor.service;

import com.variants.editor.domain.User;
import com.variants.editor.web.request.LoginRequest;
import com.variants.editor.web.request.RegisterUserRequest;
import com.variants.editor.web.response.LoginResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDetails loadUserByUsername(String username);

    LoginResponse authenticateUser(LoginRequest request);

    User registerUser(RegisterUserRequest request);

    Page<User> getUsers(Pageable pageable);

    User getByUsername(String username);

}
