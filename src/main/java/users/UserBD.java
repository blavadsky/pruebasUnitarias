package users;

import java.util.HashMap;
import java.util.Map;

public class UserBD implements UserRepository {

    Map<Integer, User> users = new HashMap<>();

    @Override
    public User saveUserR(Integer id, User user) {
        return null;
    }

    @Override
    public User getUserR(Integer id) {
        return users.get(id);
    }

    @Override
    public User updateUserR(Integer id, User user) {
        return users.replace(id,user);
    }

    @Override
    public User deleteUserR(User user) {
        return users.remove(1);
    }
}

