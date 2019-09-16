package com.variants.editor.web.controller;

import com.variants.editor.facade.VariantUserFacade;
import com.variants.editor.security.UserPrincipal;
import com.variants.editor.web.request.RegisterUserRequest;
import com.variants.editor.web.response.UserResponse;
import com.variants.editor.web.response.UserVariantsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {
    private VariantUserFacade variantUserFacade;

    @Autowired
    public void setVariantUserFacade(VariantUserFacade variantUserFacade) {
        this.variantUserFacade = variantUserFacade;
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public Resource<UserResponse> registerUser(@Valid @RequestBody RegisterUserRequest request) {
        UserResponse response = variantUserFacade.registerUser(request);

        Link link = linkTo(UserController.class).slash(response.getUsername()).withSelfRel();

        return new Resource<>(response, link);
    }

    @GetMapping(value = "/{username}" , produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public Resource<UserResponse> getUserByUUID(@PathVariable String username) {
        UserResponse response = variantUserFacade.getUserByUsername(username);

        Link link = linkTo(UserController.class).slash(response.getUsername()).withSelfRel();

        return new Resource<>(response, link);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PagedResources<UserResponse>> getUsers(Pageable pageable,
                                                                 PagedResourcesAssembler assembler) {
        Page<UserResponse> response = variantUserFacade.getUsers(pageable);

        if (!response.hasContent()) {
            PagedResources pagedResources = assembler.toEmptyResource(response, UserResponse.class);
            return new ResponseEntity<PagedResources<UserResponse>>(pagedResources, HttpStatus.OK);
        }

        return new ResponseEntity<>(assembler.toResource(response), HttpStatus.OK);
    }

    @GetMapping(value = "/variants", produces = APPLICATION_JSON_VALUE)
    public Page<UserVariantsResponse> getVariantsOfCurrentUser(Pageable pageable,
                                                               @AuthenticationPrincipal  UserPrincipal userPrincipal) {
        return variantUserFacade.getVariantsOfCurrentUser(pageable, userPrincipal);
    }
}
