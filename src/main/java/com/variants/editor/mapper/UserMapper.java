package com.variants.editor.mapper;

import com.variants.editor.domain.User;
import com.variants.editor.web.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse mapToUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getUuid()
        );
    }

    public Page<UserResponse> mapToUserResponsePage(Page<User> users) {
        return users.map(this::mapToUserResponse);
    }

}

