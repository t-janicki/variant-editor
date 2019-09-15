package com.variants.editor.service.impl;

import com.variants.editor.domain.User;
import com.variants.editor.domain.Variant;
import com.variants.editor.exception.ExistException;
import com.variants.editor.exception.NotFoundException;
import com.variants.editor.repository.VariantRepository;
import com.variants.editor.security.UserPrincipal;
import com.variants.editor.service.UserService;
import com.variants.editor.service.VariantService;
import com.variants.editor.web.request.VariantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.stream.Stream;

@Service
public class VariantServiceImpl implements VariantService {
    private VariantRepository variantRepository;
    private UserService userService;

    @Autowired
    public VariantServiceImpl(VariantRepository variantRepository,
                              UserService userService) {
        this.variantRepository = variantRepository;
        this.userService = userService;
    }

    public Variant registerVariant(VariantRequest request) {
        Variant variant = new Variant();

        validateVariant(request);

        Stream.of(variant)
                .forEach(v -> {
                    v.setPosition(request.getPosition());
                    v.setAlteration(request.getAlteration());
                    v.setChromosome(request.getChromosome());
                    v.setDescription(request.getDescription());

                    variantRepository.save(v);
                });

        return variant;
    }

    public Variant assignVariantToUser(Long variantId, String userUUID) {
        User user = userService.getByUsername(userUUID);

        Variant variant = getVariantById(variantId);

        validateVariant(variant, user);

        variant.getUsers().add(user);

        variantRepository.save(variant);

        return variant;
    }

    public Page<Variant> getAllVariants(Pageable pageable) {
        return variantRepository.findAll(pageable);
    }

    public Page<Variant> getVariantsOfCurrentUser(Pageable pageable, UserPrincipal userPrincipal)  {
        User user = userService.getByUsername(userPrincipal.getUsername());
        Page<Variant> variants = variantRepository.findAllByUsers(pageable, user);

        System.out.println(variants);
        return variants;
    }

    private void validateVariant(Variant variant, User user) {
        boolean exist = variantRepository.existsByPositionAndAlterationAndChromosomeAndUsers(
                variant.getPosition(),
                variant.getAlteration(),
                variant.getChromosome(),
                Collections.singletonList(user));

        if (exist) {
            throw new ExistException("Variant already added.");
        }
    }

    private void validateVariant(VariantRequest variant) {
        boolean exist = variantRepository.existsByPositionAndAlterationAndChromosome(
                variant.getPosition(),
                variant.getAlteration(),
                variant.getChromosome());

        if (exist) {
            throw new ExistException("Variant already exist.");
        }
    }

    private Variant getVariantById(Long variantId) {
        return variantRepository.findById(variantId)
                .orElseThrow(() -> new NotFoundException("Variant with provided id not found."));
    }
}
