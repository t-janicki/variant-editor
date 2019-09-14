package com.variants.editor.web.request;

public class LoginRequest {
    private String uuid;
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String uuid, String password) {
        this.uuid = uuid;
        this.password = password;
    }

    public String getUuid() {
        return this.uuid;
    }

    public String getPassword() {
        return this.password;
    }

}
