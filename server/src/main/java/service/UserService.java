package service;

import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.User;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public boolean insertUser(User user) {
        return userDao.insertUser(user);
    }

    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }

    public boolean deleteUser(Long id) {
        return userDao.deleteUser(id);
    }

    public List<User> getUsers() {
        return userDao.getUsers();
    }

    public String getLoginById(Long id) {
        return userDao.getUserById(id)!=null?userDao.getUserById(id).getLogin():null;
    }

    public User getIdByLogin(String login){
        return userDao.getIdByClass(login);
    }
}
