package dao.impl;

import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import persistence.User;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    JdbcTemplate jdbcTemplate;

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

    public List<User> getUsers(){
        return null;
    }
}
