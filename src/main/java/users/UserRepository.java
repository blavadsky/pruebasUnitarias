package users;

public interface UserRepository {
    User saveUserR(Integer id, User user);

    User getUserR(Integer id);

    User updateUserR(Integer id, User user);

    User deleteUserR(User user);
}
