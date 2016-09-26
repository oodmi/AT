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
        int update = jdbcTemplate.update(sql, book.getIsn(), book.getAuthor(), book.getName());
        return update != 0;
    }

    public Book getBookById(Long id) {
        String sql = "SELECT * FROM book WHERE isn = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BookMapper());
    }

    public boolean updateBook(Book book) {
        String sql = "UPDATE book SET name = ? , author = ? WHERE isn = ?";
        int update = jdbcTemplate.update(sql, book.getName(), book.getAuthor(), book.getIsn());
        return update != 0;
    }

    public boolean deleteBook(Long id) {
        String sql = "DELETE FROM book WHERE isn = ?";
        int update = jdbcTemplate.update(sql, id);
        return update != 0;
    }

    public List<Book> getBooks() {
        String sql = "SELECT * FROM book";
        return jdbcTemplate.query(sql, new BookMapper());
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
