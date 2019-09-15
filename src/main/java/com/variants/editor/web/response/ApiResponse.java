package com.variants.editor.web.response;

public class ApiResponse {
    private Boolean status;
    private String message;

    public ApiResponse() {
    }

    public ApiResponse(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
