package elso.validatorImpl;

import elso.model.User;
import elso.validator.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserNameNotContainsWhiteSpace implements Validator<User> {
    @Override
    public boolean IsValid(User object) {
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(object.getUserName());
        return !matcher.find();
    }
}
