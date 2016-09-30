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

    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    public boolean updateUser(Long id, User user) {
        return userDao.updateUser(id, user);
    }

    public boolean deleteUser(Long id) {
        return userDao.deleteUser(id);
    }

    public List<User> getUsers() {
        return userDao.getUsers();
    }

    public String getLoginById(Long id) {
        return userDao.getUserById(id).getLogin();
    }
}
