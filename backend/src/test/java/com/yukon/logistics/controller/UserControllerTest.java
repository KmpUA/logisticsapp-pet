package com.yukon.logistics.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yukon.logistics.api.rest.controller.UserController;
import com.yukon.logistics.model.dto.UserRequest;
import com.yukon.logistics.persistence.entity.Role;
import com.yukon.logistics.persistence.entity.Status;
import com.yukon.logistics.persistence.entity.User;
import com.yukon.logistics.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    @Autowired
    private ObjectMapper objectMapper;
    private static User user1;
    private static User user2;

    @BeforeEach
    void init() {
        user1 = new User(1L, "first_name_1", "second_name_1", "email_1", "password",
                "url", "phone", null, null, Role.ADMIN, Status.ACTIVE);
        user2 = new User(2L, "first_name_2", "second_name_2", "email_2", "password",
                "url", "phone", null, null, Role.ADMIN, Status.ACTIVE);
    }

    @Test
    void getAllTest() throws Exception {
        List<User> expected = new ArrayList<>();
        expected.add(user1);
        expected.add(user2);

        when(userService.findAllUsers()).thenReturn(expected);

        this.mockMvc.perform(get("/users/all")).andExpect(status().isOk());
    }

    @Test
    void getByIdTest() throws Exception {
        User expected = user1;
        when(userService.findById(expected.getId())).thenReturn(expected);
        this.mockMvc.perform(get("/users/" + expected.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(expected.getId()))
                .andExpect(jsonPath("$.firstName").value(expected.getFirstName()));
    }

    @Test
    void deleteByIdTest() throws Exception {
        doNothing().when(userService).deleteUserById(1L);

        this.mockMvc.perform(delete("/users/1")).andExpect(status().isOk());
        verify(userService, times(1)).deleteUserById(1L);
    }

    @Test
    void addUserTest() throws Exception {
        User expected = user1;
        when(userService.addUser(any(User.class))).thenReturn(expected);

        UserRequest userRequest = new UserRequest(user1.getFirstName(), user1.getLastName(),
                user1.getEmail(), user1.getPassword(), user1.getImageUrl(),
                user1.getPhone(), user1.getRole(), user1.getStatus());

        this.mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value(expected.getEmail()))
                .andExpect(jsonPath("$.firstName").value(expected.getFirstName()));
    }

    @Test
    void updateUserTest() throws Exception {
        User expected = user1;
        when(userService.updateUser(any(User.class))).thenReturn(expected);

        UserRequest userRequest = new UserRequest(user1.getFirstName(), user1.getLastName(),
                user1.getEmail(), user1.getPassword(), user1.getImageUrl(),
                user1.getPhone(), user1.getRole(), user1.getStatus());

        this.mockMvc.perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(expected.getEmail()))
                .andExpect(jsonPath("$.firstName").value(expected.getFirstName()));
    }
}
