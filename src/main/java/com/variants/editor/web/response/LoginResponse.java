package com.variants.editor.web.response;

public class LoginResponse {
    private final String jwtToken;

    public LoginResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }
    public String getToken() {
        return this.jwtToken;
    }
}
