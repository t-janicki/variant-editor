package com.variants.editor.web.request;

import javax.validation.constraints.Size;

public class RegisterUserRequest {
    @Size(min = 4, max = 60, message = "Field must be between 6-60 char length.")
    private String name;
    private String password;

    public RegisterUserRequest() {
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
