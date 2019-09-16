package com.variants.editor.service;

import com.variants.editor.domain.Variant;
import com.variants.editor.security.UserPrincipal;
import com.variants.editor.web.request.VariantRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface VariantService {

    Variant registerVariant(VariantRequest request);

    Variant assignVariantToUser(Long variantId, String username);

    Page<Variant> getAllVariants(Pageable pageable);

    Page<Variant> getVariantsOfCurrentUser(Pageable pageable, UserPrincipal userPrincipal);
}
