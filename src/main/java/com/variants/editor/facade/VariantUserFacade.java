package com.variants.editor.facade;

import com.variants.editor.domain.User;
import com.variants.editor.domain.Variant;
import com.variants.editor.mapper.UserMapper;
import com.variants.editor.mapper.VariantMapper;
import com.variants.editor.security.UserPrincipal;
import com.variants.editor.service.UserService;
import com.variants.editor.service.VariantService;
import com.variants.editor.web.request.RegisterUserRequest;
import com.variants.editor.web.request.VariantRequest;
import com.variants.editor.web.response.ApiResponse;
import com.variants.editor.web.response.UserResponse;
import com.variants.editor.web.response.UserVariantsResponse;
import com.variants.editor.web.response.VariantResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public final class VariantUserFacade {
    private VariantMapper variantMapper;
    private UserMapper userMapper;
    private UserService userService;
    private VariantService variantService;

    @Autowired
    public VariantUserFacade(VariantMapper variantMapper, UserMapper userMapper,
                             UserService userService, VariantService variantService) {
        this.variantMapper = variantMapper;
        this.userMapper = userMapper;
        this.userService = userService;
        this.variantService = variantService;
    }

    public UserResponse registerUser(RegisterUserRequest request) {
        User user = userService.registerUser(request);

        return userMapper.mapToUserResponse(user);
    }

    public Page<UserResponse> getUsers(Pageable pageable) {
        Page<User> users = userService.getUsers(pageable);

        return userMapper.mapToUserResponsePage(users);
    }

    public UserResponse getUserByUsername(String username) {
        User user = userService.getByUsername(username);

        return userMapper.mapToUserResponse(user);
    }

    public VariantResponse registerVariant(VariantRequest request) {
        Variant variant = variantService.registerVariant(request);

        return variantMapper.mapToVariantResponse(variant);
    }

    public ApiResponse assignVariantToUser(Long variantId, String username) {
        variantService.assignVariantToUser(variantId, username);
        return new ApiResponse(true, "Variant assigned to user");
    }

    public Page<VariantResponse> getVariants(Pageable pageable) {
        Page<Variant> variants = variantService.getAllVariants(pageable);

        return variantMapper.mapToVariantResponsePage(variants);
    }

    public Page<UserVariantsResponse> getVariantsOfCurrentUser(Pageable pageable, UserPrincipal userPrincipal) {
        Page<Variant> variants =  variantService.getVariantsOfCurrentUser(pageable, userPrincipal);

        return variantMapper.mapToUserVariantsResponsePage(variants);
    }
}
