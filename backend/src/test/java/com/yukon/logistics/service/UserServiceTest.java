package com.yukon.logistics.service;

import com.yukon.logistics.persistence.entity.Role;
import com.yukon.logistics.persistence.entity.Status;
import com.yukon.logistics.persistence.entity.User;
import com.yukon.logistics.persistence.repository.UserRepository;
import com.yukon.logistics.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private static UserRepository userRepository;
    private static UserServiceImpl userService;
    private static User user1;
    private static User user2;

    @BeforeEach
    void init() {
        user1 = new User(1L, "first_name_1", "second_name_1", "email_1", "password",
                "url", "phone", null, null, Role.ADMIN, Status.ACTIVE);
        user2 = new User(2L, "first_name_2", "second_name_2", "email_2", "password",
                "url", "phone", null, null, Role.ADMIN, Status.ACTIVE);

        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void addUserTest() {
        User expected = user1;
        when(userRepository.save(expected)).thenReturn(expected);

        User actual = userService.addUser(expected);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findAllCitiesTest() {
        List<User> expected = new ArrayList<>();
        expected.add(user1);
        expected.add(user2);

        when(userRepository.findAll()).thenReturn(expected);

        List<User> actual = userService.findAllUsers();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findUserByIdTest() {
        User expected = user1;
        when(userRepository.findById(expected.getId())).thenReturn(Optional.of(expected));

        User actual = userService.findById(expected.getId());

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findUserByLicenceEmailTest() {
        User expected = user2;
        when(userRepository.findByEmail(expected.getEmail())).thenReturn(Optional.of(expected));

        User actual = userService.findByEmail(expected.getEmail());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void deleteUserByIdTest() {
        doNothing().when(userRepository).deleteById(user1.getId());

        userService.deleteUserById(user1.getId());
        verify(userRepository, times(1)).deleteById(user1.getId());
    }
}
