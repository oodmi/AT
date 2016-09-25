package service.impl;

import dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.Book;
import service.BookService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookDao bookDao;

    public boolean insertBook(Book book) {
        return bookDao.insertBook(book);
    }

    public Book getBookById(Long id) {
        return bookDao.getBookById(id);
    }

    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    public boolean deleteBook(Long id) {
        return bookDao.deleteBook(id);
    }

    public List<Book> getBooks() {
        return bookDao.getBooks();
    }
}
