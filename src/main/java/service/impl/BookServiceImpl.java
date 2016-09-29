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

    @Autowired
    Long limit;

    public boolean insertBook(Book book) {
        return bookDao.insertBook(book);
    }

    public Book getBookById(Long id) {
        return bookDao.getBookById(id);
    }

    public boolean updateBook(Long id, Book book) {
        return bookDao.updateBook(id, book);
    }

    public boolean deleteBook(Long id) {
        return bookDao.deleteBook(id);
    }

    public List<Book> getBooks() {
        List<Book> books = bookDao.getBooks(limit + 5);
        limit += books.size();
        return books;
    }

    public List<Book> getFirstFiveBooks() {
        limit = 0l;
        return bookDao.getBooks(limit);
    }

    public void takeBook(String login, Long id) {
        bookDao.takeBook(login, id);
    }


    public void returnBook(Long id) {
        bookDao.returnBook(id);
    }
}
