package elso.validatorImpl;

import elso.model.User;
import elso.validator.Validator;

public class UserNameMinLengthValidator implements Validator<User> {

    private int minLength = 0;

    public UserNameMinLengthValidator(int minLength) {
        this.minLength = minLength;
    }

    @Override
    public boolean isValid(User object) {
        return object.getUserName().length() >= minLength;
    }

}
