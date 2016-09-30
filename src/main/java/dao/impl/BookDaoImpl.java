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
            update = jdbcTemplate.update(sql, book.isn, book.author, book.name);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return update != 0;
    }

    public boolean updateBook(Book book) {
        String sql = "UPDATE book SET name = ? , author = ? WHERE isn = ?";
        int update;
        try {
            update = jdbcTemplate.update(sql, book.name, book.author, book.isn);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return update != 0;
    }

    public boolean deleteBook(Long isn) {
        String sql = "DELETE FROM book WHERE isn = ?";
        int update = jdbcTemplate.update(sql, isn);
        return update != 0;
    }

    public List<Book> getBooks(Long elementNumber, Long pageSize) {
        String sql = "SELECT * FROM book ORDER BY author LIMIT ? , ?";
        List<Book> query = jdbcTemplate.query(sql, new Object[]{elementNumber, pageSize}, new BookMapper());
        return query;
    }

    public void takeBook(Long owner, Long isn) {
        String sql = "UPDATE book SET owner = ? WHERE isn = ?";
        int update = jdbcTemplate.update(sql, owner, isn);
    }

    public void returnBook(Long isn) {
        String sql = "UPDATE book SET owner = NULL WHERE isn = ?";
        int update = jdbcTemplate.update(sql, isn);
    }

    private static final class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Book book = new Book();
            book.isn = Integer.valueOf(rs.getString("isn"));
            book.author = rs.getString("author");
            book.name = rs.getString("name");
            book.ownerId = Long.valueOf(rs.getString("owner"));
            return book;
        }
    }
}
