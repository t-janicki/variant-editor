package com.variants.editor.web.controller;

import com.variants.editor.facade.VariantUserFacade;
import com.variants.editor.web.request.VariantRequest;
import com.variants.editor.web.response.UserVariantResponse;
import com.variants.editor.web.response.VariantResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin("*")
@RequestMapping("/variant")
public class VariantController {

    private VariantUserFacade variantUserFacade;

    @Autowired
    public VariantController(VariantUserFacade variantUserFacade) {
        this.variantUserFacade = variantUserFacade;
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public @ResponseBody
    VariantResponse registerVariant(@RequestBody VariantRequest request) {
        return variantUserFacade.registerVariant(request);
    }

    @PutMapping(value = "/assign/{variantId}/to/{userUUID}",
            produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public @ResponseBody
    UserVariantResponse assignVariantToUser(@PathVariable Long variantId, @PathVariable String userUUID) {
        return variantUserFacade.assignVariantToUser(variantId, userUUID);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<PagedResources<VariantResponse>> getVariants(Pageable pageable,
                                                                PagedResourcesAssembler assembler) {
        Page<VariantResponse> responses = variantUserFacade.getVariants(pageable);

        return new ResponseEntity<>(assembler.toResource(responses), HttpStatus.OK);
    }
}
