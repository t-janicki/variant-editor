package com.variants.editor.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.variants.editor.domain.User;
import com.variants.editor.repository.UserRepository;
import com.variants.editor.web.request.RegisterUserRequest;
import com.variants.editor.web.response.UserResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // for restTemplate
@ActiveProfiles("test")
public class TestUserController {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private UserRepository mockUserRepository;

    @Before
    public void init() {
        User user = new User("TestName", "Username");
        when(mockUserRepository.findByUsername("Username")).thenReturn(Optional.of(user));
    }

    @Test
    public void shouldFetchSaveUser() {
        // GIVEN
        RegisterUserRequest request = new RegisterUserRequest("TestName", "password1");

        //WHEN
        ResponseEntity<User> response = restTemplate.postForEntity("/user", request, User.class);

        //THEN
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("TestName", response.getBody().getName());
        assertEquals(36, response.getBody().getUsername().length());

        verify(mockUserRepository, times(1)).save(any(User.class));
    }


}
