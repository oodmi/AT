package dao.impl;

import dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import persistence.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("SqlDialectInspection")
@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean insertBook(Book book) {
        String sql = "INSERT INTO book(isn,author,name) VALUES(?,?,?)";
        int update;
        try {
            update = jdbcTemplate.update(sql, book.getIsn(), book.getAuthor(), book.getName());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return update != 0;
    }

    public Book getBookById(Long id) {
        String sql = "SELECT * FROM book WHERE isn = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BookMapper());
    }

    public boolean updateBook(Long id, Book book) {
        String sql = "UPDATE book SET isn = ? , name = ? , author = ? WHERE isn = ?";
        int update;
        try {
            update = jdbcTemplate.update(sql, book.getIsn(), book.getName(), book.getAuthor(), id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return update != 0;
    }

    public boolean deleteBook(Long id) {
        String sql = "DELETE FROM book WHERE isn = ?";
        int update = jdbcTemplate.update(sql, id);
        return update != 0;
    }

    public List<Book> getBooks(Long limit) {
        String sql = "SELECT * FROM book ORDER BY author LIMIT ? , 5";
        List<Book> query = jdbcTemplate.query(sql, new BookMapper(), limit);
        return query;
    }

    public void takeBook(Long owner, Long id) {
        String sql = "UPDATE book SET owner = ? WHERE isn = ?";
        int update = jdbcTemplate.update(sql, owner, id);
    }

    public void returnBook(Long id) {
        String sql = "UPDATE book SET owner = NULL WHERE isn = ?";
        int update = jdbcTemplate.update(sql, id);
    }

    private static final class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Book book = new Book();
            book.setIsn(Integer.valueOf(rs.getString("isn")));
            book.setAuthor(rs.getString("author"));
            book.setName(rs.getString("name"));
            book.setOwner(rs.getString("owner"));
            return book;
        }
    }
}
