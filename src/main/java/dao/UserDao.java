package dao;

import persistence.User;

import java.util.List;

public interface UserDao {
    boolean insertUser(User user);

    User getUserById(Long id);

    boolean updateUser(Long id, User user);

    boolean deleteUser(Long id);

    List<User> getUsers();
}
