package com.variants.editor.web.controller;

import com.variants.editor.domain.Variant;
import com.variants.editor.repository.VariantRepository;
import com.variants.editor.web.request.VariantRequest;
import com.variants.editor.web.response.VariantResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // for restTemplate
@ActiveProfiles("test")
public class TestVariantController {
    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private VariantRepository mockVariantRepository;

    @Test
    public void shouldFetchRegisterVariant() {
        // GIVEN
        VariantRequest request = new VariantRequest(
                1L,
                "Alteration",
                "Chromosome",
                "Description"
        );

        // WHEN
        ResponseEntity<VariantResponse> response = restTemplate.postForEntity("/variant", request, VariantResponse.class);

        // THEN
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Alteration", response.getBody().getAlteration());
        assertEquals("Chromosome", response.getBody().getChromosome());
        assertEquals("Description", response.getBody().getDescription());

        verify(mockVariantRepository, times(1)).save(any(Variant.class));
    }

}
