package dao.impl;

import dao.BookDao;
import org.springframework.stereotype.Repository;
import persistence.Book;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {


    public boolean insertBook(Book book) {
        return false;
    }

    public Book getBookById(Long id) {
        return null;
    }

    public void updateBook(Book book) {

    }

    public boolean deleteBook(Long id) {
        return false;
    }

    public List<Book> getBooks() {
        return null;
    }
}
