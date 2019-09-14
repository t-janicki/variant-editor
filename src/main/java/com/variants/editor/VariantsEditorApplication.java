package com.variants.editor;

import com.variants.editor.domain.Role;
import com.variants.editor.domain.RoleName;
import com.variants.editor.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VariantsEditorApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(VariantsEditorApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(VariantsEditorApplication.class, args);
    }

    @Bean
    CommandLineRunner init(RoleRepository roleRepository){
        return args -> {
            LOGGER.info("Insert RoleName to database...");
            roleRepository.save(new Role(RoleName.ROLE_USER));
        };
    }
}
