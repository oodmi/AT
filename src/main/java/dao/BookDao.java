package dao;

import persistence.Book;

import java.util.List;

public interface BookDao {
    boolean insertBook(Book book);

    Book getBookById(Long id);

    boolean updateBook(Long id, Book book);

    boolean deleteBook(Long id);

    List<Book> getBooks(Long limit);

    void takeBook(String login, Long id);

    void returnBook(Long id);
}
