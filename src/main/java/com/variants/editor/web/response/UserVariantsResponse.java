package com.variants.editor.web.response;

public final class UserVariantsResponse {
    private String description;

    public UserVariantsResponse() {
    }
    public UserVariantsResponse(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
