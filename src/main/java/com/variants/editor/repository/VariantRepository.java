package com.variants.editor.repository;

import com.variants.editor.domain.User;
import com.variants.editor.domain.Variant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Long> {

    boolean existsByPositionAndAlterationAndChromosomeAndUsers(Long position, String alternation, String chromosome, List<User> user);

    boolean existsByPositionAndAlterationAndChromosome(Long position, String alternation, String chromosome);

    Page<Variant> findAllByUsers(Pageable pageable, User user);
}
