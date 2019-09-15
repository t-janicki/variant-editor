package com.variants.editor.mapper;

import com.variants.editor.domain.User;
import com.variants.editor.web.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public final class UserMapper {

    public UserResponse mapToUserResponse(final User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getUsername()
        );
    }

    public Page<UserResponse> mapToUserResponsePage(final Page<User> users) {
        return users.map(this::mapToUserResponse);
    }

}

