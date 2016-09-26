package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import persistence.Book;
import service.BookService;

import java.util.List;

@RestController
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Book getBookById(@PathVariable("id") Long id) {
        return bookService.getBookById(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void insertBook(@RequestBody Book book) {
        bookService.insertBook(book);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateBook(@PathVariable("id") Long id, @RequestBody Book book) {
        bookService.updateBook(book);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Book> getBooks() {
        List<Book> books = bookService.getBooks();
        return books;
    }

}
