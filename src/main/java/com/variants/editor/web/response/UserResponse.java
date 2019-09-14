package com.variants.editor.web.response;

public class UserResponse {
    private Long id;
    private String name;
    private String uuid;

    public UserResponse() {
    }

    public UserResponse(Long id, String name, String uuid) {
        this.id = id;
        this.name = name;
        this.uuid = uuid;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUuid() {
        return uuid;
    }
}
