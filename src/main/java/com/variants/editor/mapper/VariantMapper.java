package com.variants.editor.mapper;

import com.variants.editor.domain.Variant;
import com.variants.editor.web.response.UserVariantsResponse;
import com.variants.editor.web.response.VariantResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class VariantMapper {

    public VariantResponse mapToVariantResponse(Variant variant) {
        return new VariantResponse(
                variant.getId(),
                variant.getPosition(),
                variant.getAlteration(),
                variant.getChromosome(),
                variant.getDescription()
        );
    }

    public UserVariantsResponse mapToUserVariantsResponse(Variant variant) {
        return new UserVariantsResponse(
                variant.getDescription()
        );
    }

     public Page<VariantResponse> mapToVariantResponsePage(Page<Variant> variants) {
        return variants.map(this::mapToVariantResponse);
     }

     public Page<UserVariantsResponse> mapToUserVariantsResponsePage(Page<Variant> variants) {
        return variants.map(this::mapToUserVariantsResponse);
     }
}
