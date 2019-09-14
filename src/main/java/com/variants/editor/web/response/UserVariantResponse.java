package com.variants.editor.web.response;

public class UserVariantResponse {
    private VariantResponse variantResponse;
    private UserResponse userResponse;

    public UserVariantResponse() {
    }

    public UserVariantResponse(VariantResponse variantResponse,
                               UserResponse userResponse) {
        this.variantResponse = variantResponse;
        this.userResponse = userResponse;
    }

    public VariantResponse getVariantResponse() {
        return variantResponse;
    }

    public UserResponse getUserResponse() {
        return userResponse;
    }
}
