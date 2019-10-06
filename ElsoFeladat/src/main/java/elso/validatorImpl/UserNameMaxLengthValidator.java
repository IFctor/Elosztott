package elso.validatorImpl;

import elso.model.User;
import elso.validator.Validator;

public class UserNameMaxLengthValidator implements Validator<User> {

    private int maxLength = Integer.MAX_VALUE;

    public UserNameMaxLengthValidator(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public boolean isValid(User object) {
        return object.getUserName().length() <= maxLength;
    }

}
