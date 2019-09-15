package com.variants.editor.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.variants.editor.domain.Variant;
import com.variants.editor.repository.VariantRepository;
import com.variants.editor.web.request.VariantRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // for restTemplate
@ActiveProfiles("test")
public class TestVariantController {
    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private VariantRepository mockVariantRepository;

    @Before
    public void init() {
        Variant variant = new Variant(
                1L,
                1L,
                "Alteration",
                "Chromosome",
                "Description"
        );

        when(mockVariantRepository.findById(1L)).thenReturn(Optional.of(variant));
    }

    @Test
    public void shouldFetchSaveVariant() {
        // GIVEN
        VariantRequest request = new VariantRequest(
                1L,
                "Alteration",
                "Chromosome",
                "Description"
        );

        // WHEN
        ResponseEntity<Variant> response = restTemplate.postForEntity("/variant", request, Variant.class);

        // THEN
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Alteration", response.getBody().getAlteration());
        assertEquals("Chromosome", response.getBody().getChromosome());
        assertEquals("Description", response.getBody().getDescription());

        verify(mockVariantRepository, times(1)).save(any(Variant.class));
    }
}
