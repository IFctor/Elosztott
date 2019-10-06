package elso.controller;

import elso.model.User;
import elso.service.UserService;
import elso.validator.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void createUser() {
        Validator<User> userValidator = mock(Validator.class);
        when(userValidator.isValid(anyObject())).thenReturn(false);
        UserService userService = mock(UserService.class);

        UserController userController = new UserController(userService,userValidator);

        userController.createUser("asdasda");

        verify(userService, never()).save(any(User.class));
    }
}