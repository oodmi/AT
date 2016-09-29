package service.impl;

import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.User;
import service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    public boolean insertUser(User user) {
        return userDao.insertUser(user);
    }

    public User getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    public boolean updateUser(String login, User user) {
        return userDao.updateUser(login, user);
    }

    public boolean deleteUser(String login) {
        return userDao.deleteUser(login);
    }

    public List<String> getUsers() {
        return userDao.getUsers();
    }
}
