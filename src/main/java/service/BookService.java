package service;

import persistence.Book;

import java.util.List;

public interface BookService {

    boolean insertBook(Book book);

    Book getBookById(Long id);

    boolean updateBook(Long id, Book book);

    boolean deleteBook(Long id);

    List<Book> getBooks();

    List<Book> getFirstFiveBooks();

    void takeBook(String login, Long id);

    void returnBook( Long id);

}
