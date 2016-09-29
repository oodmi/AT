package dao;

import persistence.User;

import java.util.List;

public interface UserDao {
    boolean insertUser(User user);

    User getUserByLogin(String login);

    boolean updateUser(String login, User user);

    boolean deleteUser(String login);

    List<String> getUsers();
}
