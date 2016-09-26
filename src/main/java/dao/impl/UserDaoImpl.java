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
        int update = jdbcTemplate.update(sql, user.getLogin(), user.getPassword());
        return update != 0;
    }

    public User getUserByLogin(String login) {
        String sql = "select * from user where login = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{login}, new UserMapper());
    }

    public boolean updateUser(User user) {
        String sql = "update user set password = ? where login = ?";
        int update = jdbcTemplate.update(sql, user.getPassword(), user.getLogin());
        return update != 0;
    }

    public boolean deleteUser(String login) {
        String sql = "delete from user where login = ?";
        int update = jdbcTemplate.update(sql, login);
        return update != 0;
    }

    public List<User> getUsers() {
        String sql = "select * from user";
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
