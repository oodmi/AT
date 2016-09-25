package service;

import persistence.Book;

import java.util.List;

public interface BookService {

    boolean insertBook(Book book);

    Book getBookById(Long id);

    void updateBook(Book book);

    boolean deleteBook(Long id);

    List<Book> getBooks();
}
