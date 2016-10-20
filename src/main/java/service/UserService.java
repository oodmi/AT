package service;

import dao.UserDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.User;

import java.util.List;

@Service
public class UserService {

    private  static final Logger LOG = Logger.getLogger(UserService.class);

    @Autowired
    UserDao userDao;

    public boolean insertUser(User user) {
        LOG.info("insert user {id:"  + user.getId() + ";login:" + user.getLogin()+";password:"+ user.getPassword()+"}");
        return userDao.insertUser(user);
    }

    public User getUserById(Long id) {
        LOG.info("get user by id: " + id);
        return userDao.getUserById(id);
    }

    public boolean updateUser(User user) {
        LOG.info("update user {id:"  + user.getId() + ";login:" + user.getLogin()+";password:"+ user.getPassword()+"}");
        return userDao.updateUser(user);
    }

    public boolean deleteUser(Long id) {
        LOG.info("delete user by id:" + id);
        return userDao.deleteUser(id);
    }

    public List<User> getUsers() {
        LOG.info("Get all Users");
        return userDao.getUsers();
    }

    public String getLoginById(Long id) {
        LOG.info("Get user login by id:" + id);
        return userDao.getUserById(id)!=null?userDao.getUserById(id).getLogin():null;
    }

    public User getIdByLogin(String login){
        LOG.info("Get user id by login:" + login);
        return userDao.getIdByClass(login);
    }
}
