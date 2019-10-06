package elso.serviceImpl;

import elso.model.User;
import elso.service.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public void save(User user) {
        System.out.println("elmentve: " + user.getUserName());
    }
}
