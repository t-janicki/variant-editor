package com.variants.editor.web.response;

public final class UserResponse {
    private Long id;
    private String name;
    private String username;

    public UserResponse() {
    }

    public UserResponse(Long id, String name, String username) {
        this.id = id;
        this.name = name;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }
}
