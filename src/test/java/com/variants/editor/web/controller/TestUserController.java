package com.variants.editor.web.controller;

import com.variants.editor.domain.User;
import com.variants.editor.repository.UserRepository;
import com.variants.editor.web.request.RegisterUserRequest;
import com.variants.editor.web.response.UserResponse;
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
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // for restTemplate
@ActiveProfiles("test")
public class TestUserController {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private UserRepository mockUserRepository;

    @Test
    public void shouldFetchSaveUser() {
        // GIVEN
        RegisterUserRequest request = new RegisterUserRequest("TestName", "password1");

        //WHEN
        ResponseEntity<UserResponse> response = restTemplate.postForEntity("/user", request, UserResponse.class);

        //THEN
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("TestName", response.getBody().getName());
        assertEquals(36, response.getBody().getUsername().length());

        verify(mockUserRepository, times(1)).save(any(User.class));
    }

    @Test
    public void shouldReturnStatus401() {
        // GIVEN
        HttpHeaders headers = new HttpHeaders();
        headers.add(CONTENT_TYPE, APPLICATION_JSON_VALUE);

        //WHEN
        HttpEntity<User> requestEntity = new HttpEntity<>(null, headers);

        ResponseEntity<UserResponse> result = restTemplate.exchange("/user/username", HttpMethod.GET, requestEntity, UserResponse.class);

        //THEN
        assertEquals(401, result.getStatusCodeValue());
    }

}
