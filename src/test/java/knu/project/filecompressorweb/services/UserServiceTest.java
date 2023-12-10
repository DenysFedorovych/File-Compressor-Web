package knu.project.filecompressorweb.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import knu.project.filecompressorweb.api.model.UserModel;
import knu.project.filecompressorweb.domain.entity.User;
import knu.project.filecompressorweb.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private ObjectMapper objectMapper;

    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository, objectMapper);
    }

    @Test
    void testRegisterUser() {
        UserModel userModel = new UserModel("Test", "Test");
        User user = new User();

        when(objectMapper.convertValue(userModel, User.class)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(objectMapper.convertValue(user, UserModel.class)).thenReturn(userModel);

        UserModel result = userService.registerUser(userModel);

        assertEquals(userModel, result);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testGetUserById() {
        Long userId = 1L;
        Optional<User> user = Optional.of(new User(/* parameters */));
        when(userRepository.findById(userId)).thenReturn(user);

        Optional<User> result = userService.getUserById(userId);

        assertEquals(user, result);
    }

    @Test
    void testLoginUser() {
        String username = "testUser";
        String password = "password";
        Optional<User> user = Optional.of(new User());
        user.get().setId(1L);

        when(userRepository.findByUsernameAndPassword(username, password)).thenReturn(user);

        Optional<Long> result = userService.loginUser(username, password);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get());
    }

    @Test
    void testDeleteUser() {
        Long userId = 1L;

        userService.deleteUser(userId);

        verify(userRepository).deleteById(userId);
    }
}
