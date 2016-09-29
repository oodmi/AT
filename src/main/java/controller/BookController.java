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
    public boolean insertBook(@RequestBody Book book) {
        return bookService.insertBook(book);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public boolean updateBook(@PathVariable("id") Long id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteBook(@PathVariable("id") Long id) {
        return bookService.deleteBook(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @RequestMapping(value = "/first/five/", method = RequestMethod.GET)
    public List<Book> getFirstFiveBooks() {
        return bookService.getFirstFiveBooks();
    }

    @RequestMapping(value = "/take/{id}", method = RequestMethod.PUT)
    public void takeBook(@PathVariable("id") Long id, @RequestBody String login) {
        bookService.takeBook(login, id);
    }

    @RequestMapping(value = "/return/{id}", method = RequestMethod.PUT)
    public void returnBook(@PathVariable("id") Long id) {
        bookService.returnBook(id);
    }

}
