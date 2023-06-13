package users;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private UserRepository userRepository;
    Map<Integer, User> users = new HashMap<>();

    public User saveUser(Integer id, User user) {
        if(users.get(id) == null) {
            User user1 = new User(user.getPassword(),user.getEmail());
            users.put(id, user);
            return user;
        }
        return users.get(id);
    }

    public User getUser(Integer id) {
        return userRepository.getUserR(id);
    }

    public User updateUser(Integer id, User user) {
        users.replace(id,user);
        return userRepository.updateUserR(id, user);
    }

    public boolean deteleUser(User user) {
        return userRepository.deleteUserR(user) != null;
    }

    public UserService (UserRepository userRepository){
        this.userRepository = userRepository;
    }
}
