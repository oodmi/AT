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

    public User getUserById(Long id) {
        String sql = "select id, login from user where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new UserMapper());
    }

    public boolean updateUser(User user) {
        String sql = "update user set login = ?, password = ? where id = ?";
        int update = 0;
        try {
            update = jdbcTemplate.update(sql, user.getLogin(), user.getPassword(), user.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return update != 0;
    }

    public boolean deleteUser(Long id) {
        String sql = "delete from user where id = ?";
        int update;
        try {
            update = jdbcTemplate.update(sql, id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return update != 0;
    }

    public List<User> getUsers() {
        String sql = "select id, login from user order by login";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    private static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getString("id"));
            user.setLogin(rs.getString("login"));
            return user;
        }
    }
}
