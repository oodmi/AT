package service;

import persistence.User;

import java.util.List;

public interface UserService {

    boolean insertUser(User user);

    User getUserByLogin(String login);

    void updateUser(User user);

    boolean deleteUser(String login);

    List<User> getUsers();
}
