package elso.main;

import elso.controller.UserController;
import elso.model.User;
import elso.service.UserService;
import elso.serviceImpl.UserServiceImpl;
import elso.validator.Validator;
import elso.validatorImpl.UserNameMinLengthValidator;
import elso.validatorImpl.UserNameNotContainsWhiteSpace;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        ArrayList<Validator<User>> validators = new ArrayList<Validator<User>>() {
            {
                add(new UserNameNotContainsWhiteSpace());
                add(new UserNameMinLengthValidator(6));
            }
        };

        UserService userService = new UserServiceImpl();

        UserController userController = new UserController(userService, validators);
        UserController withoutValidatorsUserController = new UserController(userService);

        ArrayList<String> testUserNames = new ArrayList<String>() {
            {
                add("with white space");
                add("ValidUserName");
                add("notVal");
                add("sdtzgcvu89uhvbjioöüpokjn");
                add("'+!%/=()Ö");
                add(null);
            }
        };

        for (String actualUserName : testUserNames) {
            userController.createUser(actualUserName);
            withoutValidatorsUserController.createUser(actualUserName);
        }
    }
}
