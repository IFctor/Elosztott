package elso.controller;

import elso.model.User;
import elso.validator.Validator;
import elso.validatorImpl.UserNameMinLengthValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserNameMinLengthValidatorTest {

    private Validator<User> userValidator;

    @BeforeEach
    public void beforeEach()
    {
        userValidator=new UserNameMinLengthValidator(6);
    }

    @Test
    void isValid_length_5() {
        User user = new User("12345");
        assertFalse(userValidator.isValid(user));

    }

    @Test
    void isValid_length_6() {
        User user = new User("123456");
        assertTrue(userValidator.isValid(user));

    }
}