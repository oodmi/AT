package dao.impl;

import dao.UserDao;
import org.springframework.stereotype.Repository;
import persistence.User;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    public boolean insertUser(User user) {
        return false;
    }

    public User getUserByLogin(String login) {
        return null;
    }

    public void updateUser(User user) {

    }

    public boolean deleteUser(String login) {
        return false;
    }

    public List<User> getUsers() {
        return null;
    }
}
