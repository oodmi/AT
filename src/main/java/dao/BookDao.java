package dao;

import persistence.Book;

import java.util.List;

public interface BookDao {
    boolean insertBook(Book book);

    boolean updateBook(Book book);

    boolean deleteBook(Long isn);

    List<Book> getBooks(Long elementNumber, Long limit);

    void takeBook(Long owner, Long isn);

    void returnBook(Long isn);
}
