package com.yukon.logistics;
/*
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yukon.logistics.api.rest.controller.UserController;
import com.yukon.logistics.persistence.entity.User;
import com.yukon.logistics.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.empty;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
@ActiveProfiles("test")
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<User> userList;

    @BeforeEach
    void setUp() {
        this.userList = new ArrayList<>();
        this.userList.add(User.builder().id(1L).firstName("Max").lastName("Paraniuk").email("user1@gmail.com").build());
        this.userList.add(User.builder().id(2L).firstName("Xam").lastName("Kuinarap").email("user2@gmail.com").build());
        this.userList.add(User.builder().id(3L).firstName("Amx").lastName("Kuina").email("user3@gmail.com").build());
    }

    @Test
    void shouldFetchAllUsers() throws Exception {

        given(userService.findAllUsers()).willReturn(userList);

        this.mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(userList.size())));
    }

    @Test
    void shouldFetchOneUserById() throws Exception {
        final Long userId = 1L;
        final User user = User.builder().id(1L).firstName("Max").lastName("Paraniuk").email("user1@gmail.com").build();

        given(userService.findById(userId)).willReturn(user);

        this.mockMvc.perform(get("/users/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(user.getEmail())))
                .andExpect(jsonPath("$.name", is(user.getFirstName())));
    }

    @Test
    void shouldReturn404WhenFindUserById() throws Exception {
        final Long userId = 1L;
        given(userService.findById(userId)).willReturn((User) empty());

        this.mockMvc.perform(get("/user/{id}", userId))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateNewUser() throws Exception {
        given(userService.addUser(any(User.class))).willAnswer((invocation) -> invocation.getArgument(0));

        User user = User.builder().id(1L).firstName("Max").lastName("Paraniuk").email("user1@gmail.com").build();

        this.mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email", is(user.getEmail())))
                .andExpect(jsonPath("$.password", is(user.getPassword())))
                .andExpect(jsonPath("$.firstname", is(user.getFirstName())))
                .andExpect(jsonPath("$.lastname", is(user.getLastName())));
        ;
    }

    @Test
    void shouldUpdateUser() throws Exception {
        Long userId = 1L;
        User user = User.builder().id(1L).firstName("Max").lastName("Paraniuk").email("user1@gmail.com").build();
        given(userService.findById(userId)).willReturn(user);
        given(userService.updateUser(any(User.class))).willAnswer((invocation) -> invocation.getArgument(0));

        this.mockMvc.perform(put("/users/{id}", user.getId())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(user.getEmail())))
                .andExpect(jsonPath("$.password", is(user.getPassword())))
                .andExpect(jsonPath("$.firstname", is(user.getFirstName())));

    }

    @Test
    void shouldDeleteUser() throws Exception {
        Long userId = 1L;
        User user = User.builder().id(1L).firstName("Max").lastName("Paraniuk").email("user1@gmail.com").build();
        given(userService.findById(userId)).willReturn(user);
        doNothing().when(userService).deleteUserById(user.getId());

        this.mockMvc.perform(delete("/users/{id}", user.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(user.getEmail())))
                .andExpect(jsonPath("$.password", is(user.getPassword())))
                .andExpect(jsonPath("$.firstname", is(user.getFirstName())));

    }

}
*/