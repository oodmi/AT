package dao.impl;

import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import persistence.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("SqlDialectInspection")
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean insertUser(User user) {
        String sql = "insert into user(login,password) values(?,?)";
        int update;
        try {
            update = jdbcTemplate.update(sql, user.getLogin(), user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return update != 0;
    }

    public User getUserByLogin(String login) {
        String sql = "select * from user where login = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{login}, new UserMapper());
    }

    public boolean updateUser(String login, User user) {
        String sql = "update user set password = ? login = ? where login = ?";
        int update;
        try {
            update = jdbcTemplate.update(sql, user.getPassword(), user.getLogin(), login);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return update != 0;
    }

    public boolean deleteUser(String login) {
        String sql = "delete from user where login = ?";
        int update;
        try {
            update = jdbcTemplate.update(sql, login);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return update != 0;
    }

    public List<User> getUsers() {
        String sql = "select * from user order by login";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    private static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    }
}
